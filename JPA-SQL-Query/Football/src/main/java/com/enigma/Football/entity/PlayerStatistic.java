package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity
public class PlayerStatistic extends Timestamp {
    @GeneratedValue(generator = "player_statistic_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "player_statistic_id", strategy = "uuid")
    @Id
    private String id;

    @ManyToOne
    @JoinColumn
    private Player player;

    @Column
    private Integer goal;

    @Column
    private Integer assist;

    @Column
    private Integer yellowCard;

    @Column
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

    @PrePersist
    public void prePersist() {
        goal = 0;
        assist = 0;
        yellowCard = 0;
        redCard = 0;
    }
}
