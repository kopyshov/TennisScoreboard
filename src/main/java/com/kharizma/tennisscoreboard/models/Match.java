package com.kharizma.tennisscoreboard.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "MATCH")
public class Match {
    @Id
    @Column(name = "MATCH_ID")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "PLAYER_ONE", referencedColumnName = "PLAYER_ID")
    private Player playerOne;

    @OneToOne
    @JoinColumn(name = "PLAYER_TWO", referencedColumnName = "PLAYER_ID")
    private Player playerTwo;

    @OneToOne
    @JoinColumn(name = "WINNER", referencedColumnName = "PLAYER_ID")
    private Player winner;

    @Transient
    private int[] score = new int[2];

    public Match() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
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

    public int[] getScore() {
        return score;
    }
    public void playerOneWonPoint(int[] score) {
        score[0] = score[0] + 1;
    }

    public void playerTwoWonPoint(int[] score) {
        score[1] = score[1] + 1;
    }
}

