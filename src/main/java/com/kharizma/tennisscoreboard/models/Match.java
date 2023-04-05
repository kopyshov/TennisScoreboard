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
    @Transient
    private boolean setOneIsGoing;

    @Transient
    private final static int PLAYER_ONE = 1;
    @Transient
    private final static int PLAYER_TWO = 2;

    public Match() {
        this.score = new Score();
        setOneIsGoing = true;
    }

    public void playerWon (int player) {
        if (player == PLAYER_ONE) {
            if (isBigger()) {
                if (score.getPlayerOneGames() > 4) {
                    score.setPlayerOneSets(score.getPlayerOneSets() + 1);
                    score.setPlayerOneGames(0);
                    score.setPlayerTwoGames(0);
                    setOneIsGoing = false;
                } else {
                    score.wonPoint(PLAYER_ONE);
                }
            } else {
                score.setPlayerOneGames(score.getPlayerOneGames() + 1);
                setOneIsGoing = false;
            }
        }
        if (player == PLAYER_TWO) {
            if(isBigger()) {
                if (score.getPlayerTwoGames() > 4) {
                    score.setPlayerTwoSets(score.getPlayerTwoSets() + 1);
                    score.setPlayerOneGames(0);
                    score.setPlayerTwoGames(0);
                    setOneIsGoing = false;
                } else {
                    score.wonPoint(PLAYER_TWO);
                }
            } else {
                score.setPlayerTwoGames(score.getPlayerTwoGames() + 1);
                setOneIsGoing = false;
            }
        }
    }

    private boolean isBigger() {
        return Math.abs(score.getPlayerOneGames() - score.getPlayerTwoGames()) > 1;
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

    public boolean isSetOneIsGoing() {
        return setOneIsGoing;
    }

    public Score getScore() {
        return score;
    }
}

