package com.gamic.Endurably.Gamic.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateTournamentRequestDto {

    @NotBlank
    private String name;
    @NotBlank
    private String gameName;
    @NotNull @Future
    private LocalDateTime startDate;
    private String prizePool;
    @Min(1)
    private int teamSize;
    private String rules;

    @NotNull
    private List<RegistrationFieldDto> requiredFields;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(String prizePool) {
        this.prizePool = prizePool;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public List<RegistrationFieldDto> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<RegistrationFieldDto> requiredFields) {
        this.requiredFields = requiredFields;
    }
}

