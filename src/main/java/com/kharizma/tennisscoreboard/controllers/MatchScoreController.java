package com.kharizma.tennisscoreboard.controllers;

import com.kharizma.tennisscoreboard.models.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class MatchScoreController implements IController {

    private Match currentMatch;

    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    public static final int SET_ONE = 0;
    public static final int SET_TWO = 1;
    private static final int[] PTS_ARRAY = new int[] {0, 15, 30, 40};


    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        setAttributes(servletRequest);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/match.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }



    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID uuid = UUID.fromString(servletRequest.getParameter("playerId"));
        if(currentMatch.getPlayerOne().getId().equals(uuid)) {
            currentMatch.getScore().wonPoints(PLAYER_ONE);
        }
        if (currentMatch.getPlayerTwo().getId().equals(uuid)){
            currentMatch.getScore().wonPoints(PLAYER_TWO);
        }
        if(currentMatch.getScore().isFinishMatch()) {
            setAttributes(servletRequest);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/gameover.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
            CurrentMatchController.getInstance().getMatches().remove(currentMatch.getId());
            return;
        }
        executeGet(servletRequest, servletResponse);
    }

    private void setAttributes(HttpServletRequest servletRequest) {
        CurrentMatchController currentMatchController = CurrentMatchController.getInstance();
        Map<UUID, Match> matches = currentMatchController.getMatches();
        UUID currentMatchId = UUID.fromString(servletRequest.getParameter("uuid"));
        currentMatch = matches.get(currentMatchId);
        servletRequest.setAttribute("GameUuid", currentMatchId);
        servletRequest.setAttribute("playerOneName", currentMatch.getPlayerOne().getName());
        servletRequest.setAttribute("playerOneSetOne", currentMatch.getScore().games[SET_ONE][PLAYER_ONE]);
        servletRequest.setAttribute("playerOneSetTwo", currentMatch.getScore().games[SET_TWO][PLAYER_ONE]);
        servletRequest.setAttribute("playerOnePoints", PTS_ARRAY[currentMatch.getScore().points[PLAYER_ONE]]);

        servletRequest.setAttribute("playerTwoName", currentMatch.getPlayerTwo().getName());
        servletRequest.setAttribute("playerTwoSetOne", currentMatch.getScore().games[SET_ONE][PLAYER_TWO]);
        servletRequest.setAttribute("playerTwoSetTwo", currentMatch.getScore().games[SET_TWO][PLAYER_TWO]);
        servletRequest.setAttribute("playerTwoPoints", PTS_ARRAY[currentMatch.getScore().points[PLAYER_TWO]]);

        servletRequest.setAttribute("playerOneId", currentMatch.getPlayerOne().getId().toString());
        servletRequest.setAttribute("playerTwoId", currentMatch.getPlayerTwo().getId().toString());
    }
}
