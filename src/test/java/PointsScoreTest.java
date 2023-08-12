import com.kharizma.tennisscoreboard.matches.score.GameState;
import com.kharizma.tennisscoreboard.matches.score.Points;
import com.kharizma.tennisscoreboard.matches.score.PointsScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointsScoreTest {
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;

     @Test
    public void advantagePointsTest() {
        PointsScore pointsScore = new PointsScore();
        int player = PLAYER_ONE;
        for (int i = 0; i < 27; i++) {
            pointsScore.upPoints(player);
            player = player^1;
        }
         assertEquals(Points.ADVANTAGE, pointsScore.getPlayerScore(PLAYER_ONE));
    }

    @Test
    public void winPlayerAfterAdvantage() {
        PointsScore pointsScore = new PointsScore();
        int player = PLAYER_ONE;
        for (int i = 0; i < 27; i++) {
            pointsScore.upPoints(player);
            player = player^1;
        }
        assertEquals(GameState.PLAYER_ONE_WIN, pointsScore.upPoints(player^1));
    }
}
