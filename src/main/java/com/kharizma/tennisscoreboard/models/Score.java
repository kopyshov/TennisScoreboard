package com.kharizma.tennisscoreboard.models;

public class Score {
    private int playerOneSets;
    private int playerOneGames;
    private Point playerOnePoints;

    private int playerTwoSets;
    private int playerTwoGames;
    private Point playerTwoPoints;

    private boolean gameIsEqual;

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
        this.playerOnePoints = Point.ZERO;
        this.playerOneGames = 0;

        this.playerTwoPoints = Point.ZERO;
        this.playerTwoGames = 0;

    }
    public void countGames(Players pointWinner) {

    }
    public void wonPoint(int player) {
        Point[] values = Point.values();
        switch (player) {
            case 1 -> {
                if (gameIsEqual) {
                    if (isBigger()) {
                        playerOneWonGame();
                    } else {
                        if (playerOnePoints.equals(Point.THIRD)) {
                            playerOnePoints = Point.FIRST;
                            playerTwoPoints = Point.ZERO;
                        } else {
                            int currentIndex = playerOnePoints.ordinal();
                            int nextIndex = currentIndex + 1;
                            playerOnePoints = values[nextIndex];
                            if(isBigger()) {
                                playerOneWonGame();
                            }
                        }
                    }
                } else {
                    int currentIndex = playerOnePoints.ordinal();
                    if(currentIndex == 4) {
                        playerOneWonGame();
                    } else {
                        int nextIndex = (currentIndex + 1) % values.length;
                        playerOnePoints = values[nextIndex];
                    }
                }
            }
            case 2 -> {
                if (gameIsEqual) {
                    if (isBigger()) {
                        playerTwoWonGame();
                    } else {
                        if (playerTwoPoints.equals(Point.THIRD)) {
                            playerOnePoints = Point.ZERO;
                            playerTwoPoints = Point.FIRST;
                        } else {
                            int currentIndex = playerTwoPoints.ordinal();
                            int nextIndex = currentIndex + 1;
                            playerTwoPoints = values[nextIndex];
                            if(isBigger()) {
                                playerTwoWonGame();
                            }
                        }
                    }
                } else {
                    int currentIndex = playerTwoPoints.ordinal();
                    if(currentIndex == 4) {
                        playerTwoWonGame();
                    } else {
                        int nextIndex = (currentIndex + 1) % values.length;
                        playerTwoPoints = values[nextIndex];
                    }
                }
            }
        }
        if(playerOnePoints.equals(Point.THIRD) & playerTwoPoints.equals(Point.THIRD)) {
            gameIsEqual = true;
        }
    }

    private void playerOneWonGame() {
        playerOneGames++;
        playerOnePoints = Point.ZERO;
        playerTwoPoints = Point.ZERO;
    }

    private void playerTwoWonGame() {
        playerTwoGames++;
        playerOnePoints = Point.ZERO;
        playerTwoPoints = Point.ZERO;
    }

    private boolean isBigger() {
        return Math.abs(playerOnePoints.ordinal() - playerTwoPoints.ordinal()) > 1;
    }

    public void setPlayerOneGames(int playerOneGames) {
        this.playerOneGames = playerOneGames;
    }

    public void setPlayerTwoGames(int playerTwoGames) {
        this.playerTwoGames = playerTwoGames;
    }

    public int getPlayerOneSets() {
        return playerOneSets;
    }

    public void setPlayerOneSets(int playerOneSets) {
        this.playerOneSets = playerOneSets;
    }

    public int getPlayerTwoSets() {
        return playerTwoSets;
    }

    public void setPlayerTwoSets(int playerTwoSets) {
        this.playerTwoSets = playerTwoSets;
    }

    public int getPlayerOneGames() {
        return playerOneGames;
    }

    public int getPlayerOnePoints() {
        return playerOnePoints.getValue();
    }


    public int getPlayerTwoGames() {
        return playerTwoGames;
    }

    public int getPlayerTwoPoints() {
        return playerTwoPoints.getValue();
    }
}
