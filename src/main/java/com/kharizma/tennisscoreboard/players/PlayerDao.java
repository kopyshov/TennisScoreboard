package com.kharizma.tennisscoreboard.players;

import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import

import java.sql.SQLException;

public class PlayerDao {
    public Player insertPlayer(final Player player) {
        Transaction transaction = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(player);
            session.flush();
            transaction.commit();
        } catch (ConstraintViolationException e) {
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return player;
    }
}
