package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity
public class ResultStatistic extends Timestamp {
    @GeneratedValue(generator = "result_statistic_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "result_statistic_id", strategy = "uuid")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Result result;

    @Column(nullable = false)
    private Integer goal;

    @Column(nullable = false)
    private Integer assist;

    @Column(nullable = false)
    private Integer yellowCard;

    @Column(nullable = false)
    private Integer redCard;

    public String getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }

    public Integer getAssist() {
        return assist;
    }

    public void setAssist(Integer assist) {
        this.assist = assist;
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
