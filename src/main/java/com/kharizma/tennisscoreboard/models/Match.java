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

}

