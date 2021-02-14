package com.enigma.Football.models.players;

import com.enigma.Football.models.positions.PlayerPositionResponse;
import com.enigma.Football.models.teams.TeamResponse;

public class PlayerResponse {
    private String id;
    private String name;
    private Integer number;
    private PlayerPositionResponse playerPosition;
    private TeamResponse team;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PlayerPositionResponse getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPositionResponse playerPosition) {
        this.playerPosition = playerPosition;
    }

    public TeamResponse getTeam() {
        return team;
    }

    public void setTeam(TeamResponse team) {
        this.team = team;
    }
}
