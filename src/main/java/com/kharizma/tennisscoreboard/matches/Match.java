package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.matches.score.GameState;
import com.kharizma.tennisscoreboard.matches.score.MatchScore;
import com.kharizma.tennisscoreboard.players.Player;
import jakarta.persistence.*;

import java.io.*;
import java.util.Objects;
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
    private MatchScore matchScore;

    public Match() {}

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

public void setWinnerPlayer(GameState state) {
    if (state == GameState.PLAYER_ONE_WIN) {
        setWinner(getPlayerOne());
    } else {
        setWinner(getPlayerTwo());
    }
}
    public MatchScore getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(MatchScore matchScore) {
        this.matchScore = matchScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (!Objects.equals(id, match.id)) return false;
        if (!Objects.equals(playerOne, match.playerOne)) return false;
        if (!Objects.equals(playerTwo, match.playerTwo)) return false;
        if (!Objects.equals(winner, match.winner)) return false;
        return Objects.equals(matchScore, match.matchScore);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerOne != null ? playerOne.hashCode() : 0);
        result = 31 * result + (playerTwo != null ? playerTwo.hashCode() : 0);
        result = 31 * result + (winner != null ? winner.hashCode() : 0);
        result = 31 * result + (matchScore != null ? matchScore.hashCode() : 0);
        return result;
    }

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }
}
