package com.kharizma.tennisscoreboard.matches.score;

import static com.kharizma.tennisscoreboard.matches.score.GameState.PLAYER_ONE_WIN;
import static com.kharizma.tennisscoreboard.matches.score.GameState.PLAYER_TWO_WIN;

public class TiebreakGameScore extends GameScore<Integer> {

    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;


    @Override
    protected Integer getStartScore() {
        return 0;
    }

    @Override
    public GameState upPoints(int player) {
        score.set(player, getPlayerScore(player) + 1);
        Integer playerScore = getPlayerScore(player);
        if (playerScore > 6) {
            if (isBigger()) {
                return player == PLAYER_ONE ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
            }
        }
        return GameState.ON_GOING;
    }
    private boolean isBigger() {
        return Math.abs(getPlayerScore(PLAYER_ONE) - getPlayerScore(PLAYER_TWO)) > 1;
    }
    @Override
    public String getCurrentGamePoints(int player) {
        return getPlayerScore(player).toString();
    }
}
