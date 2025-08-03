package com.gamic.Endurably.Gamic.dto;

import java.time.LocalDateTime;

// DTO for displaying a summary of a tournament in a list
public class TournamentListDto {

    private Long id;
    private String name;
    private String gameName;
    private LocalDateTime startDate;
    private String prizePool;
    private int teamSize;

    // Getters and Setters...
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
}