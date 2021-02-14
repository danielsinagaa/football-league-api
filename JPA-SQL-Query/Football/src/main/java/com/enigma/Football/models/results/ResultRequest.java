package com.enigma.Football.models.results;

public class ResultRequest {
    private String matchs;
    private Integer homeGoal;
    private Integer awayGoal;

    public String getMatchs() {
        return matchs;
    }

    public void setMatchs(String matchs) {
        this.matchs = matchs;
    }

    public Integer getHomeGoal() {
        return homeGoal;
    }

    public void setHomeGoal(Integer homeGoal) {
        this.homeGoal = homeGoal;
    }

    public Integer getAwayGoal() {
        return awayGoal;
    }

    public void setAwayGoal(Integer awayGoal) {
        this.awayGoal = awayGoal;
    }
}
