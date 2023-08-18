import com.kharizma.tennisscoreboard.matches.score.RegularGameScore;
import org.junit.jupiter.api.Test;

import static com.kharizma.tennisscoreboard.matches.score.GameState.PLAYER_ONE_WIN;
import static com.kharizma.tennisscoreboard.matches.score.RegularGamePoints.ADVANTAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegularGamePointsScoreTest {
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;

     @Test
    public void advantagePointsTest() {
        RegularGameScore regularGameScore = new RegularGameScore();
        int player = PLAYER_ONE; //можно поменять на игрока PLAYER_TWO
        int games = 1; //может быть любым числом

        int nextPlayer = player;
        //игр дожно быть не меньше 8 (у каждого игрока будет по 40 очков (ровно)
        for (int i = 1; i < 8 + 2*(player + games); i++) {
            regularGameScore.upPoints(nextPlayer);
            nextPlayer = nextPlayer^1;
        }
         assertEquals(ADVANTAGE, regularGameScore.getPlayerScore(player));
    }

    @Test
    public void winPlayerAfterAdvantage() {
        RegularGameScore regularGameScore = new RegularGameScore();
        int player = PLAYER_ONE; //можно поменять на игрока PLAYER_TWO
        int games = 2; //может быть любым числом

        int nextPlayer = player;
        //игр дожно быть не меньше 8 (у каждого игрока будет по 40 очков (ровно)
        for (int i = 1; i < 8 + 2*(player + games); i++) {
            regularGameScore.upPoints(nextPlayer);
            nextPlayer = nextPlayer^1;
        }
        assertEquals(PLAYER_ONE_WIN, regularGameScore.upPoints(player));
    }
}
