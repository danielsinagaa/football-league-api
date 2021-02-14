package com.enigma.Football.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Table
@Entity
public class Fixture extends Timestamp{
    @GeneratedValue(generator = "fixture_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "fixture_id", strategy = "uuid")
    @Id
    private String id;

    private Integer week;

    public String getId() {
        return id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
