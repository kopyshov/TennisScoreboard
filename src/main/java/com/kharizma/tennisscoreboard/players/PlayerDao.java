package com.kharizma.tennisscoreboard.players;

import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class PlayerDao {
    public Player insertPlayer(Player player) {
        Transaction transaction = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(player);
            session.flush();
            transaction.commit();
        } catch (ConstraintViolationException e) {
            player = this.getPlayer(player);
            return player;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return player;
    }

    public Player getPlayer(Player player) {
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            session.find(Player.class, player.getName());
            session.flush();
        } catch (Exception e) {
            System.out.println("Не удалось найти игрока с таким именем");
            e.printStackTrace();
        }
        return player;
    }
}
