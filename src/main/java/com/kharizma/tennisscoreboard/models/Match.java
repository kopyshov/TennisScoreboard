package com.kharizma.tennisscoreboard.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    @Id
    @Column(name = "MATCH_ID")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerOne;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerTwo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player winner;

    @Transient
    private Score score;

    public Match() {
        this.score = new Score();
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        id = UUID.randomUUID();
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Score getScore() {
        return score;
    }
}

