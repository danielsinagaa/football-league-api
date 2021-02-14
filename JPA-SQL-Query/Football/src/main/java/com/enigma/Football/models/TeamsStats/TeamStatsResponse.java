package com.enigma.Football.models.TeamsStats;

import com.enigma.Football.entity.Team;
import com.enigma.Football.models.teams.TeamResponse;

public class TeamStatsResponse {
    private String id;
    private TeamResponse team;
    private Integer win;
    private Integer lose;
    private Integer draw;
    private Integer points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeamResponse getTeam() {
        return team;
    }

    public void setTeam(TeamResponse team) {
        this.team = team;
    }

    public Integer getWin() {
        return win;
    }

    public void setWin(Integer win) {
        this.win = win;
    }

    public Integer getLose() {
        return lose;
    }

    public void setLose(Integer lose) {
        this.lose = lose;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
