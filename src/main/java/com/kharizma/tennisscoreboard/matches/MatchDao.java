package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.players.PlayerDao;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.matches.score.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class MatchDao {
    private final PlayerDao playerDao;
    Match currentMatch;

    private static final String FILTER_BY_NAME = "FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String ALL_MATCHES = "FROM Match";

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
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
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

    public List<Match> getAllMatches() {
        Transaction transaction;
        List<Match> matches = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery(ALL_MATCHES, Match.class);
            matches = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
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

    public List<Match> getMatchesByNameFilter(String playerName) {
        Transaction transaction;
        List<Match> matches = null;
        try (Session session = DatabaseHandler.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Match> query = session.createQuery(FILTER_BY_NAME, Match.class);
            matches = query.setParameter("playerName", playerName).getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
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
