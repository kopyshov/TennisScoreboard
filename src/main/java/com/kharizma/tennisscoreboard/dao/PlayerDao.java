package com.kharizma.tennisscoreboard.dao;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Player;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PlayerDao {
    private static final String FIND_BY_NAME = "FROM Player WHERE name = :name ";

    public Player insertPlayer(String name) {
        Transaction transaction = null;
        Player player = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(FIND_BY_NAME);
            query.setParameter("name", name);
            try {
                player = (Player) query.getSingleResult();
            } catch (NoResultException nre) {
                player = new Player();
                player.generateId();
                player.setName(name);
            }
            session.merge(player);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return player;
    }
}
