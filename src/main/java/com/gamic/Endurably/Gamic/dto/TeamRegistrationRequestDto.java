package com.gamic.Endurably.Gamic.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TeamRegistrationRequestDto {
    
    @NotNull
    private List<FieldValueDto> teamFields;

    @NotNull
    @NotEmpty
    private List<PlayerSubmissionDto> playerSubmissions;

    public List<FieldValueDto> getTeamFields() { return teamFields; }
    public void setTeamFields(List<FieldValueDto> teamFields) { this.teamFields = teamFields; }
    public List<PlayerSubmissionDto> getPlayerSubmissions() { return playerSubmissions; }
    public void setPlayerSubmissions(List<PlayerSubmissionDto> playerSubmissions) { this.playerSubmissions = playerSubmissions; }
}
