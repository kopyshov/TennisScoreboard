package com.tennisscoreboard.matches;

import com.tennisscoreboard.players.Player;
import com.tennisscoreboard.players.PlayerDao;
import com.tennisscoreboard.util.DatabaseHandler;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.IntStream;

public enum MatchDao {
    INSTANCE;
    private static final String FILTER_BY_NAME = "FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String COUNT_FILTER_BY_NAME = "SELECT COUNT(*) FROM Match m WHERE m.playerOne.name = :playerName or m.playerTwo.name = :playerName";
    private static final String COUNT_MATCHES = "SELECT COUNT(*) FROM Match";
    private static final String ALL_MATCHES = "FROM Match";

    public void save(Match currentMatch) {
        Transaction transaction = null;
        try (Session session = DatabaseHandler.INSTANCE.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            findOrInsertPlayer(currentMatch);
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

    public static final int PLAYER_ONE = 0;
    public static final int PLAYER_TWO = 1;
    private void findOrInsertPlayer(Match match) {
/*        Player findPlayer;
        for (int player = PLAYER_ONE; player <= PLAYER_TWO; player++) {
            findPlayer = playerDao.getPlayer(match.getPlayer(player));
            if (findPlayer.getId() != null) {
                match.getPlayer(player).setId(findPlayer.getId());
            } else {
                playerDao.insertPlayer(match.getPlayer(player));
            }
        }*/
        IntStream
                .rangeClosed(PLAYER_ONE, PLAYER_TWO)
                .forEach(player -> {
                    Player findPlayer = PlayerDao.INSTANCE.getPlayer(match.getPlayer(player));
                    if (findPlayer.getId() != null) {
                        match.getPlayer(player).setId(findPlayer.getId());
                    } else {
                        PlayerDao.INSTANCE.insertPlayer(match.getPlayer(player));
                    }
                });
    }

    public Long getCountMatches() {
        Transaction transaction;
        Long countMatches = 0L;
        try (Session session = DatabaseHandler.INSTANCE.getSessionFactory().openSession()) {
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
        try (Session session = DatabaseHandler.INSTANCE.getSessionFactory().openSession()) {
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
        try (Session session = DatabaseHandler.INSTANCE.getSessionFactory().openSession()) {
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
        try (Session session = DatabaseHandler.INSTANCE.getSessionFactory().openSession()) {
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
