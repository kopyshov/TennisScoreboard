package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import com.kharizma.tennisscoreboard.matches.score.MatchScore;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.players.PlayerDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchController implements MatchController {
    public static CurrentMatchController instance;
    private final Map<UUID, Match> matches = new ConcurrentHashMap<>();
    private CurrentMatchController() {
    }
    public static CurrentMatchController getInstance() {
        if(instance == null) {
            instance = new CurrentMatchController();
        }
        return instance;
    }
    @Override
    public void executeGet( HttpServletRequest servletRequest,
                            HttpServletResponse servletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
    @Override
    public void executePost(HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws IOException {
        Player playerOne = new Player();
        playerOne.setCurrentId(UUID.randomUUID());
        String name1 = servletRequest.getParameter("name1");
        playerOne.setName(name1);

        Player playerTwo = new Player();
        playerTwo.setCurrentId(UUID.randomUUID());
        String name2 = servletRequest.getParameter("name2");
        playerTwo.setName(name2);

        Match currentMatch = new Match();
        UUID uuid = UUID.randomUUID();
        currentMatch.setId(uuid);
        currentMatch.setPlayerOne(playerOne);
        currentMatch.setPlayerTwo(playerTwo);
        currentMatch.setMatchScore(new MatchScore());

        matches.put(currentMatch.getId(), currentMatch);
        servletResponse.sendRedirect(servletRequest.getContextPath() + "/match-score?uuid=" + uuid);
    }
    public Match getMatch(UUID uuid) {
        return matches.get(uuid);
    }
    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
