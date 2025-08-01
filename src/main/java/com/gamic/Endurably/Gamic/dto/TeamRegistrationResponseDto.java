package com.gamic.Endurably.Gamic.dto;

import java.util.List;

/**
 * DTO for displaying a full team registration, including all player data.
 */
public class TeamRegistrationResponseDto {

    private Long registrationId;
    private String submittedByEmail;
    private List<FieldValueResponseDto> teamFields; // Changed to use the new response DTO
    private List<PlayerSubmissionResponseDto> playerSubmissions;

    // --- Getters and Setters ---

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getSubmittedByEmail() {
        return submittedByEmail;
    }

    public void setSubmittedByEmail(String submittedByEmail) {
        this.submittedByEmail = submittedByEmail;
    }

    public List<FieldValueResponseDto> getTeamFields() {
        return teamFields;
    }

    public void setTeamFields(List<FieldValueResponseDto> teamFields) {
        this.teamFields = teamFields;
    }

    public List<PlayerSubmissionResponseDto> getPlayerSubmissions() {
        return playerSubmissions;
    }

    public void setPlayerSubmissions(List<PlayerSubmissionResponseDto> playerSubmissions) {
        this.playerSubmissions = playerSubmissions;
    }
}
