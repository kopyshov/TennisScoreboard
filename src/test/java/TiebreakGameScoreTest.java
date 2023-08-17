import com.kharizma.tennisscoreboard.matches.score.GameState;
import com.kharizma.tennisscoreboard.matches.score.TiebreakGameScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TiebreakGameScoreTest {

    public static int PLAYER_ONE = 0;

    @Test
    public void winWithSevenPointsAndBiggerMoreTwoPoints() {
        TiebreakGameScore tiebreak = new TiebreakGameScore();
        for (int i = 1; i < 7; i++) {
            tiebreak.upPoints(PLAYER_ONE);
        }
        assertEquals(GameState.PLAYER_ONE_WIN, tiebreak.upPoints(PLAYER_ONE));
    }

    @Test
    public void tiebreakOnGoingAfterUpPointsMoreSevenEachOther() {
        TiebreakGameScore tiebreak = new TiebreakGameScore();
        int player = PLAYER_ONE;
        int games = 20; //может быть любым числом
        for (int i = 0; i < games; i++) {
            tiebreak.upPoints(player);
            player ^= 1;
        }
        assertEquals(GameState.ON_GOING, tiebreak.upPoints(player));
    }
}
