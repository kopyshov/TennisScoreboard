package com.kharizma.tennisscoreboard.controllers;

import com.kharizma.tennisscoreboard.dao.MatchDao;
import com.kharizma.tennisscoreboard.models.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CurrentMatchController implements IController {

    private final MatchDao matchDao;
    public static CurrentMatchController instance;
    private final Map<UUID, Match> matches = new HashMap<>();

    private CurrentMatchController() {
        matchDao = new MatchDao();
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
        String name1 = servletRequest.getParameter("name1");
        String name2 = servletRequest.getParameter("name2");

        Match currentMatch = matchDao.createMatch(name1, name2);
        UUID uuid = currentMatch.getId();
        matches.put(currentMatch.getId(), currentMatch);

        servletResponse.sendRedirect(servletRequest.getContextPath() + "/match-score?uuid=" + uuid);
    }

    public Map<UUID, Match> getMatches() {
        return matches;
    }

    public Match getMatch(UUID uuid) {
        return matches.get(uuid);
    }
}
