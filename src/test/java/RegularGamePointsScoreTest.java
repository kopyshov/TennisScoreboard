import com.kharizma.tennisscoreboard.matches.score.GameState;
import com.kharizma.tennisscoreboard.matches.score.RegularGamePoints;
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
        int player = PLAYER_ONE;
        for (int i = 0; i < 27; i++) {
            regularGameScore.upPoints(player);
            player = player^1;
        }
         assertEquals(ADVANTAGE, regularGameScore.getPlayerScore(PLAYER_ONE));
    }

    @Test
    public void winPlayerAfterAdvantage() {
        RegularGameScore regularGameScore = new RegularGameScore();
        int player = PLAYER_ONE;
        for (int i = 0; i < 27; i++) {
            regularGameScore.upPoints(player);
            player = player^1;
        }
        assertEquals(PLAYER_ONE_WIN, regularGameScore.upPoints(player^1));
    }
}
