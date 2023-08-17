package com.kharizma.tennisscoreboard.matches.score;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import com.kharizma.tennisscoreboard.matches.CurrentMatchController;
import com.kharizma.tennisscoreboard.matches.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ScoreController implements MatchController {

    private Match currentMatch;

    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    public static final int SET_ONE = 0;
    public static final int SET_TWO = 1;
    public static final int SET_THREE = 2;


    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        setAttributes(servletRequest);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/match.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    private GameState gameState;

    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID uuid = UUID.fromString(servletRequest.getParameter("playerId"));
        gameState = GameState.ON_GOING;
        if(currentMatch.getPlayer(PLAYER_ONE).getCurrentId().equals(uuid)) {
            gameState = currentMatch.getMatchScore().upPoints(PLAYER_ONE);
        }
        if (currentMatch.getPlayer(PLAYER_TWO).getCurrentId().equals(uuid)){
            gameState = currentMatch.getMatchScore().upPoints(PLAYER_TWO);
        }
        if(gameState != GameState.ON_GOING) {
            setAttributes(servletRequest);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/gameover.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        executeGet(servletRequest, servletResponse);
    }

    private void setAttributes(HttpServletRequest servletRequest) {
        CurrentMatchController currentMatchController = CurrentMatchController.getInstance();
        UUID currentMatchId = UUID.fromString(servletRequest.getParameter("uuid"));
        currentMatch = currentMatchController.getMatch(currentMatchId);
        currentMatch.setWinnerPlayer(gameState);
        servletRequest.setAttribute("GameUuid", currentMatchId);
        servletRequest.setAttribute("playerOneName", currentMatch.getPlayer(PLAYER_ONE).getName());
        servletRequest.setAttribute("playerOneSetOne", currentMatch.getMatchScore().getGamesScore(SET_ONE, PLAYER_ONE));
        servletRequest.setAttribute("playerOneSetTwo", currentMatch.getMatchScore().getGamesScore(SET_TWO, PLAYER_ONE));
        servletRequest.setAttribute("playerOneSetThree", currentMatch.getMatchScore().getGamesScore(SET_THREE, PLAYER_ONE));
        servletRequest.setAttribute("playerOnePoints", currentMatch.getMatchScore().getCurrentPoints(PLAYER_ONE));

        servletRequest.setAttribute("playerTwoName", currentMatch.getPlayer(PLAYER_TWO).getName());
        servletRequest.setAttribute("playerTwoSetOne", currentMatch.getMatchScore().getGamesScore(SET_ONE, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoSetTwo", currentMatch.getMatchScore().getGamesScore(SET_TWO, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoSetThree", currentMatch.getMatchScore().getGamesScore(SET_THREE, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoPoints", currentMatch.getMatchScore().getCurrentPoints(PLAYER_TWO));

        servletRequest.setAttribute("playerOneId", currentMatch.getPlayer(PLAYER_ONE).getCurrentId().toString());
        servletRequest.setAttribute("playerTwoId", currentMatch.getPlayer(PLAYER_TWO).getCurrentId().toString());
        servletRequest.setAttribute("winnerId", currentMatch.getWinner().getCurrentId().toString());
    }
}
