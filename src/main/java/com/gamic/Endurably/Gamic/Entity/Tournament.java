package com.gamic.Endurably.Gamic.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gameName;
    private LocalDateTime startDate;
    private String prizePool;
    private int teamSize;

    @Column(length = 1000)
    private String rules;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("tournament-fields")
    private List<RegistrationField> requiredFields = new ArrayList<>();

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @JsonManagedReference("tournament-registrations")
    private List<TeamRegistration> registrations = new ArrayList<>();

    public void addRegistrationField(RegistrationField field) {
        this.requiredFields.add(field);
        field.setTournament(this);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public String getPrizePool() { return prizePool; }
    public void setPrizePool(String prizePool) { this.prizePool = prizePool; }
    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }
    public String getRules() { return rules; }
    public void setRules(String rules) { this.rules = rules; }
    public List<RegistrationField> getRequiredFields() { return requiredFields; }
    public void setRequiredFields(List<RegistrationField> requiredFields) { this.requiredFields = requiredFields; }
    public List<TeamRegistration> getRegistrations() { return registrations; }
    public void setRegistrations(List<TeamRegistration> registrations) { this.registrations = registrations; }
}
