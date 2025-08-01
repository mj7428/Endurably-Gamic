package com.gamic.Endurably.Gamic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamic.Endurably.Gamic.Entity.TeamRegistration;
import com.gamic.Endurably.Gamic.dto.TeamRegistrationRequestDto;
import com.gamic.Endurably.Gamic.services.TeamRegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournaments/{tournamentId}/register")
public class TeamRegistrationController {

    private final TeamRegistrationService registrationService;

    public TeamRegistrationController(TeamRegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()") 
    public ResponseEntity<TeamRegistration> registerTeam(
            @PathVariable Long tournamentId,
            @Valid @RequestBody TeamRegistrationRequestDto requestDto) {

        TeamRegistration newRegistration = registrationService.registerTeamForTournament(tournamentId, requestDto);
        return new ResponseEntity<>(newRegistration, HttpStatus.CREATED);
    }
}

