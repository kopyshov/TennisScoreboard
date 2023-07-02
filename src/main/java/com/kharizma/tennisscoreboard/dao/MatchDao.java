package com.kharizma.tennisscoreboard.dao;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import com.kharizma.tennisscoreboard.models.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class MatchDao {
    private final PlayerDao playerDao;
    Match currentMatch;

    private static final String FILTER_BY_NAME = "FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "from Match";

    public MatchDao() {
        playerDao = new PlayerDao();
    }

    public Match createMatch(String playerName1, String playerName2) {
        Player player1 = playerDao.insertPlayer(playerName1);
        Player player2 = playerDao.insertPlayer(playerName2);

        currentMatch = new Match(UUID.randomUUID());
        currentMatch.setPlayerOne(player1);
        currentMatch.setPlayerTwo(player2);
        currentMatch.setScore(new Score());

        return currentMatch;
    }

    public void save(Match currentMatch) {
        Transaction transaction = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
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
    }

    public List getAllMatches() {
        Transaction transaction;
        List matches = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery(ALL_MATCHES).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List getAllMatchesWithOffset(int offset, int pageLimit) {
        Transaction transaction;
        List matches = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery(ALL_MATCHES)
                    .setFirstResult(offset)
                    .setMaxResults(pageLimit)
                    .list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List getMatchesByNameFilter(String playerName) {
        Transaction transaction;
        List matches = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery(FILTER_BY_NAME).setParameter("playerName", playerName).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public List getMatchesByNameFilterWithOffset(String playerName, int offset, int pageLimit) {
        Transaction transaction;
        List matches = null;
        try (Session session = DBHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            matches = session.createQuery(FILTER_BY_NAME)
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
