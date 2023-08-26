package com.tennisscoreboard.matches.score;

import com.tennisscoreboard.controllers.MatchController;
import com.tennisscoreboard.matches.CurrentMatchController;
import com.tennisscoreboard.matches.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

public class ScoreController implements MatchController {
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    CurrentMatchController currentMatchController = CurrentMatchController.INSTANCE;

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        setAttributes(servletRequest, GameState.ON_GOING);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/match.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID currentMatchId = UUID.fromString(servletRequest.getParameter("uuid"));
        Match currentMatch = currentMatchController.getMatch(currentMatchId);
        UUID uuid = UUID.fromString(servletRequest.getParameter("playerId"));
        GameState gameState = GameState.ON_GOING;
        if(currentMatch.getPlayer(PLAYER_ONE).getCurrentId().equals(uuid)) {
            gameState = currentMatch.getMatchScore().upPoints(PLAYER_ONE);
        }
        if (currentMatch.getPlayer(PLAYER_TWO).getCurrentId().equals(uuid)){
            gameState = currentMatch.getMatchScore().upPoints(PLAYER_TWO);
        }
        if(gameState != GameState.ON_GOING) {
            setAttributes(servletRequest, gameState);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/gameover.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        executeGet(servletRequest, servletResponse);
    }

    private void setAttributes(HttpServletRequest servletRequest, GameState gameState) {
        UUID currentMatchId = UUID.fromString(servletRequest.getParameter("uuid"));
        Match currentMatch = currentMatchController.getMatch(currentMatchId);
        currentMatch.setWinnerPlayer(gameState);
        servletRequest.setAttribute("match", currentMatch);
    }
}
