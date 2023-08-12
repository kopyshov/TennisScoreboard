package com.kharizma.tennisscoreboard.matches.score;

public class SetScore extends Score<Integer> {
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private boolean isTieBreak = false;
    PointsScore currentGame;
    public SetScore() {
        currentGame = new PointsScore();
    }

    @Override
    protected Integer getStartScore() {
        return 0;
    }
    @Override
    public GameState upPoints(int player) {
        if (isTieBreak) {
            score.set(player, getPlayerScore(player)+1);
            System.out.println("Счет в тай брейке: Игрок #1 - " + score.get(0));
            System.out.println("Счет в тай брейке: Игрок #2 - " + score.get(1));
            if (isBigger()) {
                isTieBreak = false;
                return player == PLAYER_ONE ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
            }
        } else {
            GameState state = currentGame.upPoints(player);
            if (state == GameState.PLAYER_ONE_WIN) {
                return gameWin(PLAYER_ONE);
            }
            if (state == GameState.PLAYER_TWO_WIN) {
                return gameWin(PLAYER_TWO);
            }
        }
        return GameState.ON_GOING;
    }

    private GameState gameWin(int player) {
        score.set(player, getPlayerScore(player)+1);
        this.currentGame = new PointsScore();

        if (getPlayerScore(player) > 5) {
            if (isBigger()) {
                return player == PLAYER_ONE ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
            }
            if (getPlayerScore(player) == 6 & getPlayerScore(player^1) == 6) {
                isTieBreak = true;
                return GameState.ON_GOING;
            }
        }
        return GameState.ON_GOING;
    }
    private boolean isBigger() {
        return Math.abs(getPlayerScore(PLAYER_ONE) - getPlayerScore(PLAYER_TWO)) > 1;
    }

    public PointsScore getCurrentGame() {
        return currentGame;
    }

    public boolean isTieBreak() {
        return isTieBreak;
    }
}
