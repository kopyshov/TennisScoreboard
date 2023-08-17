package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.matches.score.MatchScore;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.players.PlayerDao;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class MatchDao {
    private final PlayerDao playerDao;

    private static final String FILTER_BY_NAME = "FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String COUNT_FILTER_BY_NAME = "SELECT COUNT(*) FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";

    private static final String COUNT_MATCHES = "SELECT COUNT(*) FROM Match";
    private static final String ALL_MATCHES = "FROM Match";

    private static MatchDao instance;

    private MatchDao() {
        playerDao = PlayerDao.getInstance();
    }

    public static MatchDao getInstance() {
        if (instance == null) {
            instance = new MatchDao();
        }
        return instance;
    }

    public void save(Match currentMatch) {
        Transaction transaction = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Player findPlayer = playerDao.getPlayer(currentMatch.getPlayerOne());
            if (findPlayer.getId() != null) {
                currentMatch.getPlayerOne().setId(findPlayer.getId());
            } else {
                //currentMatch.getPlayerOne().generateId();
                playerDao.insertPlayer(currentMatch.getPlayerOne());
            }

            findPlayer = playerDao.getPlayer(currentMatch.getPlayerTwo());
            if (findPlayer.getId() != null) {
                currentMatch.getPlayerTwo().setId(findPlayer.getId());
            } else {
                //currentMatch.getPlayerTwo().generateId();
                playerDao.insertPlayer(currentMatch.getPlayerTwo());
            }
            System.out.println("Проверяем матч");
            System.out.println("Игрок №1");
            System.out.println(currentMatch.getPlayerOne().getId());
            System.out.println("Игрок №2");
            System.out.println(currentMatch.getPlayerTwo().getId());
            session.persist(currentMatch);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Long getCountMatches() {
        Transaction transaction;
        Long countMatches = 0L;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery(COUNT_MATCHES, Long.class);
            countMatches = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMatches;
    }

    public List<Match> getAllMatchesWithOffset(int offset, int pageLimit) {
        Transaction transaction;
        List<Match> matches = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery(ALL_MATCHES, Match.class);
            matches = query
                    .setFirstResult(offset)
                    .setMaxResults(pageLimit)
                    .getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public Long getCountMatchesByNameFilter(String playerName) {
        Transaction transaction;
        Long countMatches = 0L;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery(COUNT_FILTER_BY_NAME, Long.class);
            countMatches = query.setParameter("playerName", playerName).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMatches;
    }

    public List<Match> getMatchesByNameFilterWithOffset(String playerName, int offset, int pageLimit) {
        Transaction transaction;
        List<Match> matches = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery(FILTER_BY_NAME, Match.class);
            matches = query
                    .setParameter("playerName", playerName)
                    .setFirstResult(offset)
                    .setMaxResults(pageLimit)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }
}
