package com.tennisscoreboard.matches.score;

import java.util.EnumMap;
import static com.tennisscoreboard.matches.score.GameState.*;

public class RegularGameScore extends GameScore<RegularGamePoints> {
    private static final EnumMap<RegularGamePoints, String> ptsEnum = new EnumMap<>(RegularGamePoints.class);
    public RegularGameScore() {
    }
    static {
        ptsEnum.put(RegularGamePoints.LOVE, "0");
        ptsEnum.put(RegularGamePoints.FIFTEEN, "15");
        ptsEnum.put(RegularGamePoints.THIRTY, "30");
        ptsEnum.put(RegularGamePoints.FORTY, "40");
        ptsEnum.put(RegularGamePoints.ADVANTAGE, "AD");
    }
    @Override
    public GameState upPoints(int player) {
        RegularGamePoints playerScore = getPlayerScore(player);
        if (playerScore.ordinal() <= RegularGamePoints.THIRTY.ordinal()) {
            score.set(player, RegularGamePoints.values()[playerScore.ordinal() + 1]);
        }
        if (playerScore.ordinal() == RegularGamePoints.FORTY.ordinal()) {
            RegularGamePoints opponentScore = getPlayerScore(player^1);
            if (opponentScore == RegularGamePoints.ADVANTAGE) {
                score.set(player^1, RegularGamePoints.FORTY);
            } else if (opponentScore == RegularGamePoints.FORTY) {
                score.set(player, RegularGamePoints.ADVANTAGE);
            } else {
                return player == 0 ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
            }
        }
        if (playerScore == RegularGamePoints.ADVANTAGE) {
            return player == 0 ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
        }
        return ON_GOING;
    }
    @Override
    public RegularGamePoints getStartScore() {
        return RegularGamePoints.LOVE;
    }

    public String getCurrentGamePoints(int player) {
        return ptsEnum.get(getPlayerScore(player));
    }
}
