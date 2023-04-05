package com.kharizma.tennisscoreboard.models;

public class Score {
    private int playerOneSets;
    private int playerOneGames;
    private Point playerOnePoints;

    private int playerTwoSets;
    private int playerTwoGames;
    private Point playerTwoPoints;

    enum Point {ZERO(0), FIRST(15), SECOND(30), THIRD(40);

        private final int value;
        Point(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Players {
        ONE, TWO;
    }

    public Score() {
        this.playerOneSets = 0;
        this.playerOneGames = 0;
        this.playerOnePoints = Point.ZERO;

        this.playerTwoSets = 0;
        this.playerTwoGames = 0;
        this.playerTwoPoints = Point.ZERO;
    }

    public void wonPoint(Players player) {
        Point[] values = Point.values();
        switch (player) {
            case ONE -> {
                if(playerOnePoints == Point.THIRD) {
                    if(playerOneGames < 5) {
                        playerOneGames++;
                    } else {
                        playerOneGames = 0;
                        playerOneSets++;
                    }
                }
                int currentIndex = playerOnePoints.ordinal();
                int nextIndex = (currentIndex + 1) % values.length;
                playerOnePoints = values[nextIndex];
            }
            case TWO -> {
                if(playerTwoPoints == Point.THIRD) {
                    if(playerTwoGames < 5) {
                        playerTwoGames++;
                    } else {
                        playerTwoGames = 0;
                        playerTwoSets++;
                    }
                }
                int currentIndex = playerTwoPoints.ordinal();
                int nextIndex = (currentIndex + 1) % values.length;
                playerTwoPoints = values[nextIndex];
            }
        }
    }

    public int getPlayerOneSets() {
        return playerOneSets;
    }

    public int getPlayerOneGames() {
        return playerOneGames;
    }

    public int getPlayerOnePoints() {
        return playerOnePoints.getValue();
    }

    public int getPlayerTwoSets() {
        return playerTwoSets;
    }

    public int getPlayerTwoGames() {
        return playerTwoGames;
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints.getValue();
    }
}
