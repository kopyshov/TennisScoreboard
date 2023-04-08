package com.kharizma.tennisscoreboard.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {
    @Id
    @Column(name = "PLAYER_ID", columnDefinition = "BINARY(16)")
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
