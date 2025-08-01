package com.gamic.Endurably.Gamic.services;

import com.gamic.Endurably.Gamic.Entity.*;
import com.gamic.Endurably.Gamic.dto.TeamRegistrationRequestDto;
import com.gamic.Endurably.Gamic.repository.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamRegistrationService {

    private final TournamentRepository tournamentRepository;
    private final TeamRegistrationRepository teamRegistrationRepository;
    private final UserRepository userRepository;
    private final RegistrationFieldRepository registrationFieldRepository;

    public TeamRegistrationService(TournamentRepository tournamentRepository,
                                   TeamRegistrationRepository teamRegistrationRepository,
                                   UserRepository userRepository,
                                   RegistrationFieldRepository registrationFieldRepository) {
        this.tournamentRepository = tournamentRepository;
        this.teamRegistrationRepository = teamRegistrationRepository;
        this.userRepository = userRepository;
        this.registrationFieldRepository = registrationFieldRepository;
    }

    @Transactional
    public TeamRegistration registerTeamForTournament(Long tournamentId, TeamRegistrationRequestDto requestDto) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new IllegalStateException("Tournament not found with ID: " + tournamentId));

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentUser = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));

        TeamRegistration registration = new TeamRegistration();
        registration.setTournament(tournament);
        registration.setSubmittedBy(currentUser);

        // ✅ Process and save the team-level fields
        requestDto.getTeamFields().forEach(teamFieldDto -> {
            RegistrationField fieldDef = registrationFieldRepository.findById(teamFieldDto.getFieldDefinitionId())
                    .orElseThrow(() -> new IllegalStateException("Registration field not found with ID: " + teamFieldDto.getFieldDefinitionId()));
            
            FieldValue fieldValue = new FieldValue();
            fieldValue.setFieldDefinition(fieldDef);
            fieldValue.setValue(teamFieldDto.getValue());

            // Use the new helper to link this answer directly to the team registration
            registration.addTeamFieldValue(fieldValue);
        });

        // Process and save the player-level fields
        requestDto.getPlayerSubmissions().forEach(playerDto -> {
            PlayerSubmission playerSubmission = new PlayerSubmission();
            playerDto.getFieldValues().forEach(valueDto -> {
                RegistrationField fieldDef = registrationFieldRepository.findById(valueDto.getFieldDefinitionId())
                        .orElseThrow(() -> new IllegalStateException("Registration field not found with ID: " + valueDto.getFieldDefinitionId()));

                FieldValue fieldValue = new FieldValue();
                fieldValue.setFieldDefinition(fieldDef);
                fieldValue.setValue(valueDto.getValue());
                
                playerSubmission.addFieldValue(fieldValue);
            });
            
            registration.addPlayerSubmission(playerSubmission);
        });

        return teamRegistrationRepository.save(registration);
    }
}
