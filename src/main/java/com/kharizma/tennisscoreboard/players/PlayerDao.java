package com.kharizma.tennisscoreboard.players;

import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class PlayerDao {
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

    public Player getPlayer(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Query<Player> query = session.createQuery("from Player where name = :paramName", Player.class);
            query.setParameter("paramName", player.getName());
            player = query.uniqueResult();
            session.flush();
        } catch (Exception e) {
            return null;
        }
        return player;
    }
}
