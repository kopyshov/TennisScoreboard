package com.kharizma.tennisscoreboard.players;

import com.kharizma.tennisscoreboard.matches.MatchDao;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class PlayerDao {
    private static PlayerDao instance;
    private PlayerDao() {}
    public static PlayerDao getInstance() {
        if (instance == null) {
            instance = new PlayerDao();
        }
        return instance;
    }
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

    public static final String GET_PLAYER_BY_NAME = "from Player where name = :paramName";
    public Player getPlayer(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Query<Player> query = session.createQuery(GET_PLAYER_BY_NAME, Player.class);
            query.setParameter("paramName", player.getName());
            List<Player> players = query.getResultList();
            if (players.isEmpty()) {
                player = new Player();
            } else {
                player = players.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
}
