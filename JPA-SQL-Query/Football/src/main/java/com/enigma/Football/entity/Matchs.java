package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity
public class Matchs extends Timestamp {
    @GeneratedValue(generator = "match_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "match_id", strategy = "uuid")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fixture fixture;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Team home;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Team away;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Referee referee;

    public String getId() {
        return id;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public Referee getReferee() {
        return referee;
    }

    public void setReferee(Referee referee) {
        this.referee = referee;
    }
}
