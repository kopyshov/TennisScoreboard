package com.kharizma.tennisscoreboard.matches.score;

public abstract class GameScore<T> extends Score<T> {
    public abstract String getCurrentGamePoints(int player);
}
