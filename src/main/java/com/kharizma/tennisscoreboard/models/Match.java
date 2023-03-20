package com.kharizma.tennisscoreboard.models;

import jakarta.persistence.*;

@Entity
@Table(name = "MATCH")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCH_ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "PLAYER_ONE", referencedColumnName = "PLAYER_ID")
    private Player playerOne;

    @OneToOne
    @JoinColumn(name = "PLAYER_TWO", referencedColumnName = "PLAYER_ID")
    private Player playerTwo;

    @OneToOne
    @JoinColumn(name = "WINNER", referencedColumnName = "PLAYER_ID")
    private Player winner;


    public Long getId() {
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
}

