package com.kharizma.tennisscoreboard.matches.score.old;

public class OldScore {

    public static final int PLAYER_ONE = 0;
    public static final int PLAYER_TWO = 1;
    public static final int SET_ONE = 0;
    public static final int SET_TWO = 1;
    private static final int LOVE = 0;
    private static final int FIFTEEN = 1;
    private static final int FORTY = 3;
    public int[][] games = new int[2][2];
    public int[] points = new int[2];
    private int CURRENT_SET;
    private boolean isDeuce;
    private boolean isTieBreak;
    private boolean finishMatch;


    public OldScore() {
        finishMatch = false;
        isDeuce = false;
        isTieBreak = false;
        CURRENT_SET = SET_ONE;

        games[SET_ONE][PLAYER_ONE] = 0;
        games[SET_ONE][PLAYER_TWO] = 0;
        games[SET_TWO][PLAYER_ONE] = 0;
        games[SET_TWO][PLAYER_TWO] = 0;
    }
    public void wonPoints(int player) {
        if(isTieBreak) {
            games[CURRENT_SET][player]++;
            if(isBigger()) {
                if(++CURRENT_SET == 2) {
                    finishMatch = true;
                    return;
                }
                isTieBreak = false;
                isDeuce = false;
            }
        } else {
            upPoints(player);
            if(games[CURRENT_SET][player] > 5 & isBigger()) {
                if(++CURRENT_SET == 2) {
                    finishMatch = true;
                    return;
                }
            }
        }
        if(CURRENT_SET < 2) {
            if (games[CURRENT_SET][PLAYER_ONE] == 6 & games[CURRENT_SET][PLAYER_TWO] == 6) {
                isTieBreak = true;
            }
        }
    }
    private void upPoints(int player) {
        if(isDeuce) {
            points[player]++;
            if(isAdvantage()) {
                points[PLAYER_ONE] = LOVE;
                points[PLAYER_TWO] = LOVE;
                games[CURRENT_SET][player]++;
                isDeuce = false;
            } else {
                if(points[player] > FORTY) {
                    points[player] = FIFTEEN;
                    points[player^1] = LOVE;
                }
            }
        } else {
            if(points[player] == FORTY) {
                points[PLAYER_ONE] = LOVE;
                points[PLAYER_TWO] = LOVE;
                games[CURRENT_SET][player]++;
            } else {
                points[player]++;
            }
        }
        if(points[PLAYER_ONE] == FORTY & points[PLAYER_TWO] == FORTY) {
            isDeuce = true;
        }
    }
    private boolean isAdvantage() {
        return Math.abs(points[PLAYER_ONE] - points[PLAYER_TWO]) > 1;
    }
    private boolean isBigger() {
        return Math.abs(games[CURRENT_SET][PLAYER_ONE] - games[CURRENT_SET][PLAYER_TWO]) > 1;
    }

    public boolean isFinishMatch() {
        return finishMatch;
    }
}
