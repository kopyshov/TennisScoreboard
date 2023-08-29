package com.tennisscoreboard.matches;

import com.tennisscoreboard.matches.score.GameState;
import com.tennisscoreboard.matches.score.MatchScore;
import com.tennisscoreboard.players.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "MATCH")
@Getter
@Setter
@NoArgsConstructor
public class Match implements Serializable {
    @Id
    @Column(name = "MATCH_ID")
    private UUID id;
    @OneToOne(fetch = FetchType.EAGER)
    private Player playerOne;
    @OneToOne(fetch = FetchType.EAGER)
    private Player playerTwo;
    @OneToOne(fetch = FetchType.EAGER)
    private Player winner;
    @Transient
    private MatchScore matchScore;

    public void setWinnerPlayer(GameState state) {
        if (state == GameState.PLAYER_ONE_WIN) {
            setWinner(getPlayerOne());
        } else {
            setWinner(getPlayerTwo());
        }
    }

    public Player getPlayer(int player) {
        if (player == 0) {
            return getPlayerOne();
        }
        return getPlayerTwo();
    }

    public static Match getEmptyMatch() {
        Match emptyMatch = new Match();
        emptyMatch.setPlayerOne(Player.getEmptyPlayer());
        emptyMatch.setPlayerTwo(Player.getEmptyPlayer());
        emptyMatch.setWinner(Player.getEmptyPlayer());
        return emptyMatch;
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
}
