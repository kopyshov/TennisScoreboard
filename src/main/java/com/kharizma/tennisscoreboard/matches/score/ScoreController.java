package com.kharizma.tennisscoreboard.matches.score;

import com.kharizma.tennisscoreboard.controllers.IController;
import com.kharizma.tennisscoreboard.matches.CurrentMatchController;
import com.kharizma.tennisscoreboard.matches.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class ScoreController implements IController {

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
        if(currentMatch.getPlayerOne().getId().equals(uuid)) {
            gameState = currentMatch.getMatchScore().upPoints(PLAYER_ONE);
        }
        if (currentMatch.getPlayerTwo().getId().equals(uuid)){
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
        servletRequest.setAttribute("playerOneName", currentMatch.getPlayerOne().getName());
        servletRequest.setAttribute("playerOneSetOne", currentMatch.getMatchScore().getGamesScore(SET_ONE, PLAYER_ONE));
        servletRequest.setAttribute("playerOneSetTwo", currentMatch.getMatchScore().getGamesScore(SET_TWO, PLAYER_ONE));
        servletRequest.setAttribute("playerOneSetThree", currentMatch.getMatchScore().getGamesScore(SET_THREE, PLAYER_ONE));
        servletRequest.setAttribute("playerOnePoints", currentMatch.getMatchScore().getCurrentPoints(PLAYER_ONE));

        servletRequest.setAttribute("playerTwoName", currentMatch.getPlayerTwo().getName());
        servletRequest.setAttribute("playerTwoSetOne", currentMatch.getMatchScore().getGamesScore(SET_ONE, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoSetTwo", currentMatch.getMatchScore().getGamesScore(SET_TWO, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoSetThree", currentMatch.getMatchScore().getGamesScore(SET_THREE, PLAYER_TWO));
        servletRequest.setAttribute("playerTwoPoints", currentMatch.getMatchScore().getCurrentPoints(PLAYER_TWO));

        servletRequest.setAttribute("playerOneId", currentMatch.getPlayerOne().getId().toString());
        servletRequest.setAttribute("playerTwoId", currentMatch.getPlayerTwo().getId().toString());
        servletRequest.setAttribute("winnerId", currentMatch.getWinner().getId().toString());
    }
}
