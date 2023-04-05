package com.kharizma.tennisscoreboard.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {
    @Id
    @Column(name = "PLAYER_ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;


    public Player() {
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void generateId() {
        id = UUID.randomUUID();
    }

}
