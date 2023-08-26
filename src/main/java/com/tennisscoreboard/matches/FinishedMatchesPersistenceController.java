package com.tennisscoreboard.matches;

import com.tennisscoreboard.controllers.MatchController;
import com.tennisscoreboard.matches.dto.Page;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FinishedMatchesPersistenceController implements MatchController {
    private static final int ONE_PAGE_LIMIT = 3;

    public FinishedMatchesPersistenceController() {

    }

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        Page currentHtmlPage = generateHtmlPage(servletRequest);
        servletRequest.setAttribute("page", currentHtmlPage);

        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/matches.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    private Page generateHtmlPage(HttpServletRequest servletRequest) {

        @SuppressWarnings("unchecked")
        MatchDao matchDao = (MatchDao) servletRequest.getServletContext().getAttribute("matchDao");

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

        Page htmlPage = new Page.Builder()
                .matches(matches)
                .matchesQuantity(matchesQuantity)
                .pagesQuantity(pagesQuantity)
                .filterName(playerName)
                .build();
        return htmlPage;
    }

    private List<Match> createEmptyMatches() {
        List<Match> matches = new ArrayList<>();
        matches.add(Match.getEmptyMatch());
        return matches;
    }


    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
        MatchDao matchDao = (MatchDao) servletRequest.getServletContext().getAttribute("matchDao");
        UUID MatchUuid = UUID.fromString(servletRequest.getParameter("match-uuid"));
        CurrentMatchController currentMatchController = CurrentMatchController.INSTANCE;
        Match currentMatch = currentMatchController.getMatch(MatchUuid);
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
