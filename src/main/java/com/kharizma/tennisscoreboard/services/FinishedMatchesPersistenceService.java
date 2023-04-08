package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class FinishedMatchesPersistenceService implements IService{

    private static final String FILTER_BY_NAME = "FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "from Match";
    private static final int ONE_PAGE_LIMIT = 2;

    @Override
    public void executeGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        int pages;
        List matches;
        String playerName;
        Transaction transaction;

        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            int page = Integer.parseInt(servletRequest.getParameter("page")) - 1;
            int offset = page * ONE_PAGE_LIMIT;
            if(servletRequest.getParameter("filter_by_player_name").equals("")) {
                playerName = "";
                matches = session.createQuery(ALL_MATCHES).list();
                if(matches.size() % ONE_PAGE_LIMIT != 0) {
                    pages = matches.size() / ONE_PAGE_LIMIT + 1;
                } else {
                    pages = matches.size() / ONE_PAGE_LIMIT;
                }
                matches = session.createQuery(ALL_MATCHES)
                                 .setFirstResult(offset)
                                 .setMaxResults(ONE_PAGE_LIMIT)
                                 .list();
            } else {
                playerName = servletRequest.getParameter("filter_by_player_name");
                matches = session.createQuery(FILTER_BY_NAME).setParameter("playerName", playerName).list();
                if(matches.size() % ONE_PAGE_LIMIT != 0) {
                    pages = matches.size() / ONE_PAGE_LIMIT + 1;
                } else {
                    pages = matches.size() / ONE_PAGE_LIMIT;
                }
                matches = session.createQuery(FILTER_BY_NAME)
                                 .setParameter("playerName", playerName)
                                 .setFirstResult(offset)
                                 .setMaxResults(ONE_PAGE_LIMIT)
                                 .list();
            }
            servletRequest.setAttribute("player_name", playerName);
            servletRequest.setAttribute("matches", matches);
            servletRequest.setAttribute("pages", pages);

            transaction.commit();
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/matches.jsp");
            requestDispatcher.forward(servletRequest, servletResponse);
        } catch (Exception e) {
/*            if (transaction != null) {
                transaction.rollback();
            }*/
            e.printStackTrace();
        }
    }

    @Override
    public void executePost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        Transaction transaction = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {

            UUID MatchUuid = UUID.fromString(servletRequest.getParameter("match-uuid"));
            CurrentMatchService currentMatchService = CurrentMatchService.getInstance();
            Match currentMatch = currentMatchService.getMatch(MatchUuid);
            currentMatch.setWinnerPlayer();
            transaction = session.beginTransaction();
            session.merge(currentMatch);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
}
