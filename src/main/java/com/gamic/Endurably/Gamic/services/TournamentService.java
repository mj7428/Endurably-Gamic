package com.gamic.Endurably.Gamic.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gamic.Endurably.Gamic.Entity.FieldValue;
import com.gamic.Endurably.Gamic.Entity.PlayerSubmission;
import com.gamic.Endurably.Gamic.Entity.TeamRegistration;
import com.gamic.Endurably.Gamic.Entity.Tournament;
import com.gamic.Endurably.Gamic.dto.CreateTournamentRequestDto;
import com.gamic.Endurably.Gamic.dto.FieldValueResponseDto;
import com.gamic.Endurably.Gamic.dto.PlayerSubmissionResponseDto;
import com.gamic.Endurably.Gamic.dto.TeamRegistrationResponseDto;
import com.gamic.Endurably.Gamic.repository.TournamentRepository;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Transactional
    public Tournament createTournament(CreateTournamentRequestDto requestDto) {
        Tournament tournament = new Tournament();
        tournament.setName(requestDto.getName());
        tournament.setGameName(requestDto.getGameName());
        tournament.setStartDate(requestDto.getStartDate());
        tournament.setPrizePool(requestDto.getPrizePool());
        tournament.setTeamSize(requestDto.getTeamSize());
        tournament.setRules(requestDto.getRules());

        requestDto.getRequiredFields().forEach(fieldDto -> {
            com.gamic.Endurably.Gamic.Entity.RegistrationField field = new com.gamic.Endurably.Gamic.Entity.RegistrationField();
            field.setFieldName(fieldDto.getFieldName());
            field.setFieldType(fieldDto.getFieldType());
            field.setRequired(fieldDto.isRequired());
            tournament.addRegistrationField(field);
        });

        return tournamentRepository.save(tournament);
    }


    @Transactional(readOnly = true)
    public List<TeamRegistrationResponseDto> getRegistrationsForTournament(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalStateException("Tournament not found with ID: " + tournamentId));

        return tournament.getRegistrations().stream()
                .map(this::mapToTeamRegistrationDto)
                .collect(Collectors.toList());
    }

    private TeamRegistrationResponseDto mapToTeamRegistrationDto(TeamRegistration registration) {
        TeamRegistrationResponseDto dto = new TeamRegistrationResponseDto();
        dto.setRegistrationId(registration.getId());
        dto.setSubmittedByEmail(registration.getSubmittedBy().getEmail());

        List<FieldValueResponseDto> teamFields = registration.getTeamFieldValues().stream()
                .map(this::mapToFieldValueResponseDto)
                .collect(Collectors.toList());
        dto.setTeamFields(teamFields);

        List<PlayerSubmissionResponseDto> playerSubmissions = registration.getPlayerSubmissions().stream()
                .map(this::mapToPlayerSubmissionDto)
                .collect(Collectors.toList());
        dto.setPlayerSubmissions(playerSubmissions);

        return dto;
    }

    private PlayerSubmissionResponseDto mapToPlayerSubmissionDto(PlayerSubmission submission) {
        PlayerSubmissionResponseDto dto = new PlayerSubmissionResponseDto();
        dto.setPlayerSubmissionId(submission.getId());

        List<FieldValueResponseDto> fieldValues = submission.getFieldValues().stream()
                .map(this::mapToFieldValueResponseDto)
                .collect(Collectors.toList());
        dto.setFieldValues(fieldValues);
        
        return dto;
    }

    private FieldValueResponseDto mapToFieldValueResponseDto(FieldValue fieldValue) {
        FieldValueResponseDto dto = new FieldValueResponseDto();
        dto.setFieldName(fieldValue.getFieldDefinition().getFieldName());
        dto.setValue(fieldValue.getValue());
        return dto;
    }
}
