package com.enigma.Football.models.resultstats;

public class ResultStatsRequest {
    private String player;
    private String result;
    private Integer goal;
    private Integer assit;
    private Integer yellowCard;
    private Integer redCard;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getAssit() {
        return assit;
    }

    public void setAssit(Integer assit) {
        this.assit = assit;
    }

    public Integer getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(Integer yellowCard) {
        this.yellowCard = yellowCard;
    }

    public Integer getRedCard() {
        return redCard;
    }

    public void setRedCard(Integer redCard) {
        this.redCard = redCard;
    }
}
