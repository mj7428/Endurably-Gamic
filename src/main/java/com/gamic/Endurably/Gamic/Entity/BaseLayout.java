package com.gamic.Endurably.Gamic.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class BaseLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title; // e.g., "TH15 Anti-3 Star War Base"

    @NotNull
    private Integer townhallLevel; // e.g., 15

    @NotBlank
    private String baseLink; // The actual link from the game

    private String imageUrl; 

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Users submittedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTownhallLevel() {
        return townhallLevel;
    }

    public void setTownhallLevel(Integer townhallLevel) {
        this.townhallLevel = townhallLevel;
    }

    public String getBaseLink() {
        return baseLink;
    }

    public void setBaseLink(String baseLink) {
        this.baseLink = baseLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Users getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Users submittedBy) {
        this.submittedBy = submittedBy;
    }
}
