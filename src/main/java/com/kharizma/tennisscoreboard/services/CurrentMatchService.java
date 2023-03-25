package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CurrentMatchService implements IService {

    public static CurrentMatchService instance;
    private final SessionFactory sessionFactory;
    private final Map<UUID, Match> matches = new HashMap<>();

    private CurrentMatchService() {
        sessionFactory = DBHandler.getSessionFactory();
    }

    public static CurrentMatchService getInstance() {
        if(instance == null) {
            instance = new CurrentMatchService();
        }
        return instance;
    }
    @Override
    public void executeGet(HttpServletRequest servletRequest,
                        HttpServletResponse servletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void executePost(HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws IOException {
        String name1 = servletRequest.getParameter("name1");
        String name2 = servletRequest.getParameter("name2");

        UUID uuid = this.createMatch(name1, name2);

        servletResponse.sendRedirect(servletRequest.getContextPath() + "/match-score?uuid=" + uuid);
    }

    Match currentMatch;

    public UUID createMatch(String playerName1, String playerName2) {
        Player player1 = this.insertPlayer(playerName1);
        Player player2 = this.insertPlayer(playerName2);

        currentMatch = new Match();
        currentMatch.setPlayerOne(player1);
        currentMatch.setPlayerTwo(player2);

        matches.put(currentMatch.getId(), currentMatch);

        return currentMatch.getId();
    }

    public Player insertPlayer(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Player where name = :name ");
        query.setParameter("name", name);
        Player player;
        try {
            player = (Player) query.getSingleResult();
        } catch (NoResultException nre) {
            player = new Player();
            player.setName(name);
        }
        session.persist(player);
        session.getTransaction().commit();
        session.close();
        return player;
    }
}
