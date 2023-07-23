package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.matches.score.Score;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "MATCH")
public class Match implements Serializable {
    @Id
    @Column(name = "MATCH_ID", columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerOne;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player playerTwo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player winner;

    @Transient
    private Score score;

    public Match() {}
    public Match(UUID uuid) {
        this.id = uuid;
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

    public void setWinnerPlayer() {
        int winPointsPlayerOne = this.getScore().games[0][0] + this.getScore().games[1][0];
        int winPointsPlayerTwo = this.getScore().games[0][1] + this.getScore().games[1][1];
        if(winPointsPlayerOne > winPointsPlayerTwo) {
            setWinner(getPlayerOne());
        } else {
            setWinner(getPlayerTwo());
        }
    }

    public Score getScore() {
        return score;
    }
    public void setScore(Score score) {
        this.score = score;
    }
}

