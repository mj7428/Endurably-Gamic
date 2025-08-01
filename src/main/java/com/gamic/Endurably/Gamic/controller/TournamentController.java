package com.gamic.Endurably.Gamic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamic.Endurably.Gamic.Entity.Tournament;
import com.gamic.Endurably.Gamic.dto.CreateTournamentRequestDto;
import com.gamic.Endurably.Gamic.services.TournamentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<Tournament> createTournament(@Valid @RequestBody CreateTournamentRequestDto requestDto) {
        Tournament newTournament = tournamentService.createTournament(requestDto);
        return new ResponseEntity<>(newTournament, HttpStatus.CREATED);
    }
}
