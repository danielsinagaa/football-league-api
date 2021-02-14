package com.enigma.Football.models.results;

import com.enigma.Football.models.matchs.MatchResponse;
import com.enigma.Football.models.teams.TeamResponse;

public class ResultResponse {
    private String id;
    private MatchResponse matchs;
    private TeamResponse result;
    private Integer homeGoal;
    private Integer awayGoal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MatchResponse getMatchs() {
        return matchs;
    }

    public void setMatchs(MatchResponse matchs) {
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

    public TeamResponse getResult() {
        return result;
    }

    public void setResult(TeamResponse result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "id='" + id + '\'' +
                ", matchs=" + matchs +
                ", homeGoal=" + homeGoal +
                ", awayGoal=" + awayGoal +
                ", result=" + result +
                '}';
    }
}
