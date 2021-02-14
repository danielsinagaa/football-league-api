package com.enigma.Football.models.matchs;

import com.enigma.Football.models.fixtures.FixtureResponse;
import com.enigma.Football.models.referee.RefereeResponse;
import com.enigma.Football.models.teams.TeamResponse;

public class MatchResponse {
    private String id;
    private TeamResponse home;
    private TeamResponse away;
    private FixtureResponse fixture;
    private RefereeResponse referee;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TeamResponse getHome() {
        return home;
    }

    public void setHome(TeamResponse home) {
        this.home = home;
    }

    public TeamResponse getAway() {
        return away;
    }

    public void setAway(TeamResponse away) {
        this.away = away;
    }

    public FixtureResponse getFixture() {
        return fixture;
    }

    public void setFixture(FixtureResponse fixture) {
        this.fixture = fixture;
    }

    public RefereeResponse getReferee() {
        return referee;
    }

    public void setReferee(RefereeResponse referee) {
        this.referee = referee;
    }
}
