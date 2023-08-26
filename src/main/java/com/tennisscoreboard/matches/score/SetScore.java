package com.tennisscoreboard.matches.score;

import static com.tennisscoreboard.matches.score.GameState.*;

public class SetScore extends Score<Integer> {
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private boolean isTieBreak = false;
    GameScore<?> currentGame;
    public SetScore() {
        currentGame = new RegularGameScore();
    }

    @Override
    protected Integer getStartScore() {
        return 0;
    }
    @Override
    public GameState upPoints(int player) {
        GameState state;
        if (isTieBreak) {
            state = currentGame.upPoints(player);
            if (state == PLAYER_ONE_WIN) {
                score.set(PLAYER_ONE, getPlayerScore(PLAYER_ONE)+1);
            }
            if (state == PLAYER_TWO_WIN) {
                score.set(PLAYER_TWO, getPlayerScore(PLAYER_TWO)+1);
            }
        } else {
            state = currentGame.upPoints(player);
            if (state == PLAYER_ONE_WIN) {
                return gameWin(PLAYER_ONE);
            }
            if (state == PLAYER_TWO_WIN) {
                return gameWin(PLAYER_TWO);
            }
        }
        return state;
    }
    private GameState gameWin(int player) {
        score.set(player, getPlayerScore(player)+1);
        this.currentGame = new RegularGameScore();

        if (getPlayerScore(player) > 5) {
            if (isBigger()) {
                return player == PLAYER_ONE ? PLAYER_ONE_WIN : PLAYER_TWO_WIN;
            }
            if (getPlayerScore(player) == 6 & getPlayerScore(player^1) == 6) {
                isTieBreak = true;
                currentGame = new TiebreakGameScore();
                return ON_GOING;
            }
        }
        return ON_GOING;
    }
    private boolean isBigger() {
        return Math.abs(getPlayerScore(PLAYER_ONE) - getPlayerScore(PLAYER_TWO)) > 1;
    }
    public GameScore<?> getCurrentGame() {
        return currentGame;
    }

    public boolean isTieBreak() {
        return isTieBreak;
    }
}
