package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table
@Entity
public class TeamStatistic extends Timestamp {
    @GeneratedValue(generator = "team_statistic_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "team_statistic_id", strategy = "uuid")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn
    private Team team;

    @Column
    private Integer win;

    @Column
    private Integer lose;

    @Column
    private Integer draw;

    @Column
    private Integer points;

    public String getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
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

    @PrePersist
    public void prePersist() {
        win = 0;
        lose = 0;
        draw = 0;
        points = 0;
    }
}
