package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.dao.MatchDao;
import com.kharizma.tennisscoreboard.models.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FinishedMatchesPersistenceController implements IController {
    private static final int ONE_PAGE_LIMIT = 3;
    private final MatchDao matchDao;

    public FinishedMatchesPersistenceController() {
        matchDao = new MatchDao();
    }

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        int pages;
        List matches;
        String playerName;
        int page = Integer.parseInt(servletRequest.getParameter("page")) - 1;
        int offset = page * ONE_PAGE_LIMIT;

        if(servletRequest.getParameter("filter_by_player_name").equals("")) {
            playerName = "";
            matches = matchDao.getAllMatches();
            if(matches.size() % ONE_PAGE_LIMIT != 0) {
                pages = matches.size() / ONE_PAGE_LIMIT + 1;
            } else {
                pages = matches.size() / ONE_PAGE_LIMIT;
            }
            matches = matchDao.getAllMatchesWithOffset(offset, ONE_PAGE_LIMIT);
        } else {
            playerName = servletRequest.getParameter("filter_by_player_name");
            matches = matchDao.getMatchesByNameFilter(playerName);
            if(matches.size() % ONE_PAGE_LIMIT != 0) {
                pages = matches.size() / ONE_PAGE_LIMIT + 1;
            } else {
                pages = matches.size() / ONE_PAGE_LIMIT;
            }
            matches = matchDao.getMatchesByNameFilterWithOffset(playerName, offset, ONE_PAGE_LIMIT);
        }
        servletRequest.setAttribute("player_name", playerName);
        servletRequest.setAttribute("matches", matches);
        servletRequest.setAttribute("pages", pages);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/matches.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID MatchUuid = UUID.fromString(servletRequest.getParameter("match-uuid"));
        CurrentMatchController currentMatchController = CurrentMatchController.getInstance();
        Match currentMatch = currentMatchController.getMatch(MatchUuid);
        currentMatch.setWinnerPlayer();
        MatchDao matchDao = new MatchDao();
        matchDao.save(currentMatch);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
}
