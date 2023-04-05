package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.models.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;


public class MatchScoreCalculationService implements IService{

    private CurrentMatchService currentMatchService;
    private Map<UUID, Match> matches;
    private Match currentMatch;

    private final static int PLAYER_ONE = 1;
    private final static int PLAYER_TWO = 2;

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
            currentMatch.playerWon(PLAYER_ONE);
        }
        if (currentMatch.getPlayerTwo().getId().equals(uuid)){
            currentMatch.playerWon(PLAYER_TWO);
        }
        if(currentMatch.getScore().getPlayerOneSets() == 2 || currentMatch.getScore().getPlayerTwoSets() == 2) {
            setAttributes(servletRequest);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/gameover.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        executeGet(servletRequest, servletResponse);
    }

    private void setAttributes(HttpServletRequest servletRequest) {
        currentMatchService = CurrentMatchService.getInstance();
        matches = currentMatchService.getMatches();
        UUID currentMatchId = UUID.fromString(servletRequest.getParameter("uuid"));
        currentMatch = matches.get(currentMatchId);
        servletRequest.setAttribute("GameUuid", currentMatchId);
        servletRequest.setAttribute("playerOneName", currentMatch.getPlayerOne().getName());
        servletRequest.setAttribute("playerOneSets", currentMatch.getScore().getPlayerOneSets());
        servletRequest.setAttribute("playerOneGames", currentMatch.getScore().getPlayerOneGames());
        servletRequest.setAttribute("playerOnePoints", currentMatch.getScore().getPlayerOnePoints());

        servletRequest.setAttribute("playerTwoName", currentMatch.getPlayerTwo().getName());
        servletRequest.setAttribute("playerTwoSets", currentMatch.getScore().getPlayerTwoSets());
        servletRequest.setAttribute("playerTwoGames", currentMatch.getScore().getPlayerTwoGames());
        servletRequest.setAttribute("playerTwoPoints", currentMatch.getScore().getPlayerTwoPoints());

        servletRequest.setAttribute("playerOneId", currentMatch.getPlayerOne().getId().toString());
        servletRequest.setAttribute("playerTwoId", currentMatch.getPlayerTwo().getId().toString());
    }
}
