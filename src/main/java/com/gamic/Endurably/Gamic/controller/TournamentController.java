package com.gamic.Endurably.Gamic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamic.Endurably.Gamic.Entity.Tournament;
import com.gamic.Endurably.Gamic.dto.CreateTournamentRequestDto;
import com.gamic.Endurably.Gamic.dto.TeamRegistrationResponseDto;
import com.gamic.Endurably.Gamic.repository.TournamentRepository;
import com.gamic.Endurably.Gamic.services.TournamentService;

import com.gamic.Endurably.Gamic.dto.TournamentListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;
    private final TournamentRepository tournamentRepository;

    public TournamentController(TournamentService tournamentService, TournamentRepository tournamentRepository) {
        this.tournamentService = tournamentService;
        this.tournamentRepository = tournamentRepository;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tournament> createTournament(@Valid @RequestBody CreateTournamentRequestDto requestDto) {
        Tournament newTournament = tournamentService.createTournament(requestDto);
        return new ResponseEntity<>(newTournament, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        return tournament.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{tournamentId}/registrations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeamRegistrationResponseDto>> getRegistrations(@PathVariable Long tournamentId) {
        List<TeamRegistrationResponseDto> registrations = tournamentService.getRegistrationsForTournament(tournamentId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping
    public ResponseEntity<Page<TournamentListDto>> getAllTournaments(Pageable pageable) {
        Page<TournamentListDto> tournaments = tournamentService.findAllTournaments(pageable);
        return ResponseEntity.ok(tournaments);
    }
}
