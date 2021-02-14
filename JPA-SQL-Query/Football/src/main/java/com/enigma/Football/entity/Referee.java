package com.enigma.Football.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table
@Entity
public class Referee extends Timestamp {
    @GeneratedValue(generator = "ref_id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "ref_id", strategy = "uuid")
    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
