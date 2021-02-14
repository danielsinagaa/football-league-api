package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity
public class Result extends Timestamp {
    @GeneratedValue(generator = "result_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "result_id", strategy = "uuid")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Matchs matchs;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Team result;

    @Column(nullable = false)
    private Integer homeGoal;

    @Column(nullable = false)
    private Integer awayGoal;

    public String getId() {
        return id;
    }

    public Matchs getMatchs() {
        return matchs;
    }

    public void setMatchs(Matchs matchs) {
        this.matchs = matchs;
    }

    public Team getResult() {
        return result;
    }

    public void setResult(Team result) {
        this.result = result;
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
