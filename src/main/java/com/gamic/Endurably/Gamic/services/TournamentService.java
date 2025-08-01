package com.gamic.Endurably.Gamic.services;

import com.gamic.Endurably.Gamic.Entity.RegistrationField;
import com.gamic.Endurably.Gamic.Entity.Tournament;
import com.gamic.Endurably.Gamic.dto.CreateTournamentRequestDto;
import com.gamic.Endurably.Gamic.repository.TournamentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            RegistrationField field = new RegistrationField();
            field.setFieldName(fieldDto.getFieldName());
            field.setFieldType(fieldDto.getFieldType());
            field.setRequired(fieldDto.isRequired());
            tournament.addRegistrationField(field);
        });

        return tournamentRepository.save(tournament);
    }
}
