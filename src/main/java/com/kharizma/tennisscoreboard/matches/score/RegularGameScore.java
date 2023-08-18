package com.kharizma.tennisscoreboard.matches.score;

import java.util.EnumMap;
import static com.kharizma.tennisscoreboard.matches.score.GameState.*;
import static com.kharizma.tennisscoreboard.matches.score.RegularGamePoints.*;

public class RegularGameScore extends GameScore<RegularGamePoints> {
    private static final EnumMap<RegularGamePoints, String> ptsEnum = new EnumMap<>(RegularGamePoints.class);
    public RegularGameScore() {
    }
    static {
        ptsEnum.put(LOVE, "0");
        ptsEnum.put(FIFTEEN, "15");
        ptsEnum.put(THIRTY, "30");
        ptsEnum.put(FORTY, "40");
        ptsEnum.put(ADVANTAGE, "AD");
    }
    @Override
    public GameState upPoints(int player) {
        RegularGamePoints playerScore = getPlayerScore(player);
        if (playerScore.ordinal() <= THIRTY.ordinal()) {
            score.set(player, RegularGamePoints.values()[playerScore.ordinal() + 1]);
        }
        if (playerScore.ordinal() == FORTY.ordinal()) {
            RegularGamePoints opponentScore = getPlayerScore(player^1);
            if (opponentScore == ADVANTAGE) {
                score.set(player^1, FORTY);
            } else if (opponentScore == FORTY) {
                score.set(player, ADVANTAGE);
            } else {
                return player == 0 ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
            }
        }
        if (playerScore == ADVANTAGE) {
            return player == 0 ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return ON_GOING;
    }
    @Override
    public RegularGamePoints getStartScore() {
        return LOVE;
    }

    public String getCurrentGamePoints(int player) {
        return ptsEnum.get(getPlayerScore(player));
    }
}
