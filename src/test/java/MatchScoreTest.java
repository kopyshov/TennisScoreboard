import com.tennisscoreboard.matches.score.GameState;
import com.tennisscoreboard.matches.score.MatchScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchScoreTest {
    public static int SET_ONE = 0;
    public static int SET_TWO = 1;
    public static int SET_THREE = 2;
    public static int PLAYER_ONE = 0;
    public static int PLAYER_TWO = 1;

    @Test
    public void firstPlayerWonTwoSetsInRow() {
        //Здесь Игрок #1 выигрывает 2 сета подряд и соответсвенно сет #3 не начинается
        MatchScore matchScore = new MatchScore();
        for (int i = 0; i < 47; i++) {
            matchScore.upPoints(PLAYER_ONE);
        }
        assertEquals(GameState.PLAYER_ONE_WIN, matchScore.upPoints(PLAYER_ONE));
        System.out.println();
        System.out.println("Игрок #1 выиграл два сета подряд");
        showMatchScore(matchScore);
    }

    @Test
    public void firstPlayerWonTwoSet() {
        //Здесь игроки выигрывают сеты поочереди и соответсвенно играется сет #3 на победу
        MatchScore matchScore = new MatchScore();
        for (int i = 0; i < 47; i++) {
            matchScore.upPoints(PLAYER_ONE);
        }
        for (int i = 0; i < 47; i++) {
            matchScore.upPoints(PLAYER_TWO);
        }
        for (int i = 0; i < 23; i++) {
            matchScore.upPoints(PLAYER_ONE);
        }
        assertEquals(GameState.PLAYER_ONE_WIN, matchScore.upPoints(PLAYER_ONE));
        showMatchScore(matchScore);
    }

    private void showMatchScore(MatchScore matchScore) {
        System.out.println("Счет матча:");
        System.out.println("       Игрок 1 --- Игрок 2");
        System.out.println("Сет #1    " + matchScore.getGamesScore(SET_ONE, PLAYER_ONE) + " -------- " + matchScore.getGamesScore(SET_ONE, PLAYER_TWO));
        System.out.println("Сет #2    " + matchScore.getGamesScore(SET_TWO, PLAYER_ONE) + " -------- " + matchScore.getGamesScore(SET_TWO, PLAYER_TWO));
        System.out.println("Сет #3    " + matchScore.getGamesScore(SET_THREE, PLAYER_ONE) + " -------- " + matchScore.getGamesScore(SET_THREE, PLAYER_TWO));
    }
}
