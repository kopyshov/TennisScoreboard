package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import com.kharizma.tennisscoreboard.matches.dto.Page;
import com.kharizma.tennisscoreboard.players.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

public class FinishedMatchesPersistenceController implements MatchController {
    private static final int ONE_PAGE_LIMIT = 3;
    private final MatchDao matchDao;

    public FinishedMatchesPersistenceController() {
        matchDao = MatchDao.getInstance();
    }

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {

        Page currentHtmlPage = generateHtmlPage(servletRequest);
        servletRequest.setAttribute("page", currentHtmlPage);

        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/matches.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    private Page generateHtmlPage(HttpServletRequest servletRequest) {
        long pagesQuantity = 1;
        List<Match> matches;
        Long matchesQuantity = 0L;
        String playerName = "";

        int ordinalPage = Integer.parseInt(servletRequest.getParameter("page"));
        int offset = (ordinalPage - 1) * ONE_PAGE_LIMIT;
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();

        if (parameterMap.containsKey("filter_by_player_name")) {
            playerName = servletRequest.getParameter("filter_by_player_name");
            matchesQuantity = matchDao.getCountMatchesByNameFilter(playerName);
            matches = matchDao.getMatchesByNameFilterWithOffset(playerName, offset, ONE_PAGE_LIMIT);
        } else {
            matchesQuantity = matchDao.getCountMatches();
            matches = matchDao.getAllMatchesWithOffset(offset, ONE_PAGE_LIMIT);
        }
        if (matches.isEmpty()) {
            matches = createEmptyMatches();
        }
        pagesQuantity = countPages(matchesQuantity);

        Page htmlPage = new Page();
        htmlPage.setMatches(matches);
        htmlPage.setMatchesQuantity(matchesQuantity);
        htmlPage.setPagesQuantity(pagesQuantity);
        htmlPage.setFilterName(playerName);
        return htmlPage;
    }

    private List<Match> createEmptyMatches() {
        List<Match> matches = new ArrayList<>();
        Match emptyMatch = new Match();
        Player emptyPlayer = new Player();
        emptyPlayer.setName("-");
        emptyMatch.setPlayerOne(emptyPlayer);
        emptyMatch.setPlayerTwo(emptyPlayer);
        emptyMatch.setWinner(emptyPlayer);
        matches.add(emptyMatch);
        return matches;
    }


    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID MatchUuid = UUID.fromString(servletRequest.getParameter("match-uuid"));
        CurrentMatchController currentMatchController = CurrentMatchController.getInstance();
        Match currentMatch = currentMatchController.getMatch(MatchUuid);
        MatchDao matchDao = MatchDao.getInstance();
        matchDao.save(currentMatch);
        currentMatchController.removeMatch(currentMatch.getId());
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    private long countPages(Long matchesQuantity) {
        if(matchesQuantity % ONE_PAGE_LIMIT != 0) {
            return matchesQuantity / ONE_PAGE_LIMIT + 1;
        } else {
            return matchesQuantity / ONE_PAGE_LIMIT;
        }
    }
}
