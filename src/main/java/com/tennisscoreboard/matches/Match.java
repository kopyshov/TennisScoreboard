package com.tennisscoreboard.matches;

import com.tennisscoreboard.matches.score.GameState;
import com.tennisscoreboard.matches.score.MatchScore;
import com.tennisscoreboard.players.Player;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
import java.util.UUID;

@Entity
@Table(name = "MATCH")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }
    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
