package com.gamic.Endurably.Gamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TeamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonBackReference("tournament-registrations")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users submittedBy;

    // This list holds team-level answers (e.g., Clan Name, Clan Tag)
    @OneToMany(mappedBy = "teamRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("team-values")
    private List<FieldValue> teamFieldValues = new ArrayList<>();

    // This list holds the submissions for each player on the team
    @OneToMany(mappedBy = "teamRegistration", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("registration-players")
    private List<PlayerSubmission> playerSubmissions = new ArrayList<>();

    // --- Helper Methods ---
    public void addPlayerSubmission(PlayerSubmission submission) {
        this.playerSubmissions.add(submission);
        submission.setTeamRegistration(this);
    }
    
    public void addTeamFieldValue(FieldValue value) {
        this.teamFieldValues.add(value);
        value.setTeamRegistration(this);
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Users getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Users submittedBy) {
        this.submittedBy = submittedBy;
    }

    public List<FieldValue> getTeamFieldValues() {
        return teamFieldValues;
    }

    public void setTeamFieldValues(List<FieldValue> teamFieldValues) {
        this.teamFieldValues = teamFieldValues;
    }

    public List<PlayerSubmission> getPlayerSubmissions() {
        return playerSubmissions;
    }

    public void setPlayerSubmissions(List<PlayerSubmission> playerSubmissions) {
        this.playerSubmissions = playerSubmissions;
    }
}
