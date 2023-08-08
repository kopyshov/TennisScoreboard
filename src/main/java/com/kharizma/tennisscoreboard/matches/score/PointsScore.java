package com.kharizma.tennisscoreboard.matches.score;

import java.util.EnumMap;

public class PointsScore extends Score<Points> {
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private static final EnumMap<Points, String> ptsEnum = new EnumMap<>(Points.class);
    public PointsScore() {
        score.set(PLAYER_ONE, Points.LOVE);
        score.set(PLAYER_TWO, Points.LOVE);
    }

    static {
        ptsEnum.put(Points.LOVE, "0");
        ptsEnum.put(Points.FIFTEEN, "15");
        ptsEnum.put(Points.THIRTY, "30");
        ptsEnum.put(Points.FORTY, "40");
        ptsEnum.put(Points.ADVANTAGE, "AD");
    }

    @Override
    public GameState upPoints(int player) {
        Points playerScore = getPlayerScore(player);

        if (playerScore.ordinal() <= Points.THIRTY.ordinal()) {
            score.set(player, Points.values()[playerScore.ordinal() + 1]);
        }
        if (playerScore.ordinal() == Points.FORTY.ordinal()) {
            Points opponentScore = getPlayerScore(player^1);
            if (opponentScore == Points.ADVANTAGE) {
                score.set(player^1, Points.FORTY);
            } else if (opponentScore == Points.FORTY) {
                score.set(player, Points.ADVANTAGE);
            } else {
                return player == 0 ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
            }
        }
        if (playerScore == Points.ADVANTAGE) {
            return player == 0 ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
        }
        return GameState.ON_GOING;
    }
    @Override
    public Points getStartScore() {
        return Points.LOVE;
    }

    public String getPoints(int player) {
        return ptsEnum.get(getPlayerScore(player));
    }
}
