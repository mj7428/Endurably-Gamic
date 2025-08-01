package com.gamic.Endurably.Gamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "field_definition_id")
    private RegistrationField fieldDefinition;

    @ManyToOne
    @JoinColumn(name = "team_registration_id")
    @JsonBackReference("team-values")
    private TeamRegistration teamRegistration;

    @ManyToOne
    @JoinColumn(name = "player_submission_id", nullable = true)
    @JsonBackReference("submission-values")
    private PlayerSubmission playerSubmission;

    private String value;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationField getFieldDefinition() {
        return fieldDefinition;
    }

    public void setFieldDefinition(RegistrationField fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }

    public TeamRegistration getTeamRegistration() {
        return teamRegistration;
    }

    public void setTeamRegistration(TeamRegistration teamRegistration) {
        this.teamRegistration = teamRegistration;
    }

    public PlayerSubmission getPlayerSubmission() {
        return playerSubmission;
    }

    public void setPlayerSubmission(PlayerSubmission playerSubmission) {
        this.playerSubmission = playerSubmission;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
