package com.kharizma.tennisscoreboard.matches.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {
    protected final List<T> score = new ArrayList<>();

    public Score() {
        score.add(getStartScore());
        score.add(getStartScore());
    }
    protected abstract T getStartScore();
    public T getPlayerScore(int player) {
        return score.get(player);
    }
    protected abstract GameState upPoints(int player);
}
