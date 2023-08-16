package com.kharizma.tennisscoreboard.players;

import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    public static final String GET_PLAYERS_BY_FILTER_NAME = "from Player where name = :plOneName OR name = :plTwoName";
    public Player insertPlayer(Player player) throws HibernateException {
        Transaction transaction;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            session.flush();
            transaction.commit();
        }
        return player;
    }

    public List<Player> getPlayers(Match match) {
        List<Player> players = new ArrayList<>();
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Query<Player> query = session.createQuery(GET_PLAYERS_BY_FILTER_NAME, Player.class);
            query.setParameter("plOneName", match.getPlayerOne().getName());
            query.setParameter("plTwoName", match.getPlayerTwo().getName());
            players = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }
}
