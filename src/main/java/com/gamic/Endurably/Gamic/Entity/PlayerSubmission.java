package com.gamic.Endurably.Gamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_registration_id")
    @JsonBackReference("registration-players")
    private TeamRegistration teamRegistration;

    @OneToMany(mappedBy = "playerSubmission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("submission-values")
    private List<FieldValue> fieldValues = new ArrayList<>();

    public void addFieldValue(FieldValue value) {
        this.fieldValues.add(value);
        value.setPlayerSubmission(this);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TeamRegistration getTeamRegistration() { return teamRegistration; }
    public void setTeamRegistration(TeamRegistration teamRegistration) { this.teamRegistration = teamRegistration; }
    public List<FieldValue> getFieldValues() { return fieldValues; }
    public void setFieldValues(List<FieldValue> fieldValues) { this.fieldValues = fieldValues; }
}
