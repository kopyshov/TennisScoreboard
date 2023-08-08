package com.kharizma.tennisscoreboard.matches.score;

import java.util.HashMap;
import java.util.Map;

public class MatchScore extends Score<Integer> {
    SetScore tennisSet;
    private final Map<Integer, SetScore> sets = new HashMap<>();
    public static final int PLAYER_ONE = 0;
    public static final int PLAYER_TWO = 1;
    public static final int SET_ONE = 0;
    public static final int SET_TWO = 1;
    public static final int SET_THREE = 2;
    private int CURRENT_SET;

    public MatchScore() {
        sets.put(SET_ONE, new SetScore());
        sets.put(SET_TWO, new SetScore());
        sets.put(SET_THREE, new SetScore());
        CURRENT_SET = SET_ONE;
        tennisSet = sets.get(CURRENT_SET);
    }

    @Override
    protected Integer getStartScore() {
        return 0;
    }

    @Override
    protected GameState upPoints(int player) {
        GameState state = tennisSet.upPoints(player);
        if (state == GameState.PLAYER_ONE_WIN) {
            if (CURRENT_SET != SET_THREE) {
                CURRENT_SET++;
            }
            return setWin(PLAYER_ONE);
        }
        if (state == GameState.PLAYER_TWO_WIN) {
            if (CURRENT_SET != SET_THREE) {
                CURRENT_SET++;
            }
            return setWin(PLAYER_TWO);
        }
        return GameState.ON_GOING;
    }

    private GameState setWin(int player) {
        score.set(player, getPlayerScore(player)+1);
        tennisSet = getSets().get(CURRENT_SET);
        if (this.getPlayerScore(player) > 1) {
            return player == PLAYER_ONE ? GameState.PLAYER_ONE_WIN : GameState.PLAYER_TWO_WIN;
        }
        return GameState.ON_GOING;
    }

    private Map<Integer, SetScore> getSets() {
        return sets;
    }

    public SetScore getTennisSet() {
        return tennisSet;
    }

    public Integer getGamesScore(int set, int player) {
        return getSets().get(set).getPlayerScore(player);
    }

    public String getCurrentPoints(int player) {
        return this.getTennisSet().getCurrentGame().getPoints(player);
    }
}
