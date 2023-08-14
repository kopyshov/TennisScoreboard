package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import com.kharizma.tennisscoreboard.players.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FinishedMatchesPersistenceController implements MatchController {
    private static final int ONE_PAGE_LIMIT = 3;
    private final MatchDao matchDao;

    public FinishedMatchesPersistenceController() {
        matchDao = MatchDao.getInstance();
    }

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        long pagesQuantity;
        List<Match> matches;
        Long matchesQuantity;
        String playerName;
        int page = Integer.parseInt(servletRequest.getParameter("page")) - 1;
        int offset = page * ONE_PAGE_LIMIT;
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();

        if (parameterMap.containsKey("filter_by_player_name")) {
            playerName = servletRequest.getParameter("filter_by_player_name");
            matchesQuantity = matchDao.getCountMatchesByNameFilter(playerName);
            pagesQuantity = countPages(matchesQuantity);
            matches = matchDao.getMatchesByNameFilterWithOffset(playerName, offset, ONE_PAGE_LIMIT);
            if(matches.isEmpty()){
                Match emptyMatch = new Match();
                Player emptyPlayer = new Player();
                emptyPlayer.setName("-");
                emptyMatch.setPlayerOne(emptyPlayer);
                emptyMatch.setPlayerTwo(emptyPlayer);
                emptyMatch.setWinner(emptyPlayer);
                matches.add(emptyMatch);
            }
        } else {
            playerName = "";
            matchesQuantity = matchDao.getCountMatches();
            pagesQuantity = countPages(matchesQuantity);
            matches = matchDao.getAllMatchesWithOffset(offset, ONE_PAGE_LIMIT);
        }
        servletRequest.setAttribute("player_name", playerName);
        servletRequest.setAttribute("matches", matches);
        servletRequest.setAttribute("pages", pagesQuantity);

        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/matches.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }



    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        UUID MatchUuid = UUID.fromString(servletRequest.getParameter("match-uuid"));
        CurrentMatchController currentMatchController = CurrentMatchController.getInstance();
        Match currentMatch = currentMatchController.getMatch(MatchUuid);
        MatchDao matchDao = MatchDao.getInstance();
        matchDao.save(currentMatch);
        CurrentMatchController.getInstance().removeMatch(currentMatch.getId());
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
