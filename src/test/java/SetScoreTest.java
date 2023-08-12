import com.kharizma.tennisscoreboard.matches.score.GameState;
import com.kharizma.tennisscoreboard.matches.score.SetScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SetScoreTest {
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;

    @Test
    public void tieBreakTest() {
        SetScore pointsScore = new SetScore();
        GameState state = tieBreakOn(pointsScore);
        assertEquals(GameState.ON_GOING, state);
        assertTrue(pointsScore.isTieBreak());
    }

    @Test
    public void afterTieBreakAdvantage() {
        SetScore pointsScore = new SetScore();
        GameState state = tieBreakOn(pointsScore);
        for (int i = 0; i < 5; i++) {
            pointsScore.upPoints(PLAYER_ONE);
            pointsScore.upPoints(PLAYER_TWO);
        }
        assertEquals(GameState.ON_GOING, state);
        assertTrue(pointsScore.isTieBreak());
    }

    @Test
    public void afterTieBreakPlayerOneWon() {
        SetScore pointsScore = new SetScore();
        tieBreakOn(pointsScore);
        for (int i = 0; i < 1; i++) {
            pointsScore.upPoints(PLAYER_ONE);
        }
        assertEquals(GameState.PLAYER_ONE_WIN, pointsScore.upPoints(PLAYER_ONE));
        assertFalse(pointsScore.isTieBreak());
    }

    private GameState tieBreakOn(SetScore pointsScore) {
        GameState state = GameState.ON_GOING;
        for (int i = 0; i < 21; i++) {
            pointsScore.upPoints(PLAYER_ONE);
        }
        for (int i = 0; i < 21; i++) {
            pointsScore.upPoints(PLAYER_TWO);
        }
        for (int i = 0; i < 5; i++) {
            pointsScore.upPoints(PLAYER_ONE);
        }
        for (int i = 0; i < 4; i++) {
            state = pointsScore.upPoints(PLAYER_TWO);
        }
        System.out.println("Tiebreak points: ");
        System.out.print(pointsScore.getPlayerScore(PLAYER_ONE) + " - ");
        System.out.println(pointsScore.getPlayerScore(PLAYER_TWO));
        return state;
    }
}


