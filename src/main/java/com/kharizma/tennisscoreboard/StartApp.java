package com.kharizma.tennisscoreboard;

import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.matches.MatchDao;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.players.PlayerDao;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionListener;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

@WebListener
public class StartApp implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    public StartApp() {}

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        //Добавляет тестовые матчи
        addSomeMatches();
    }

    public void addSomeMatches() {
        try(Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            MatchDao matchDao = MatchDao.INSTANCE;
            PlayerDao playerDaoEnum = PlayerDao.INSTANCE;

            Player p1 = new Player();
            p1.setName("А. Петров");

            Player p2 = new Player();
            p2.setName("И. Иванов");

            Player p3 = new Player();
            p3.setName("С. Сидоров");

            Player p4 = new Player();
            p4.setName("У. Ушаков");

            Player p5 = new Player();
            p5.setName("Б. Баранов");

            playerDaoEnum.insertPlayer(p1);
            playerDaoEnum.insertPlayer(p2);
            playerDaoEnum.insertPlayer(p3);
            playerDaoEnum.insertPlayer(p4);
            playerDaoEnum.insertPlayer(p5);

            Match m1 = new Match();
            m1.setId(UUID.randomUUID());
            m1.setPlayerOne(p1);
            m1.setPlayerTwo(p2);
            m1.setWinner(p2);

            Match m2 = new Match();
            m2.setId(UUID.randomUUID());
            m2.setPlayerOne(p1);
            m2.setPlayerTwo(p3);
            m2.setWinner(p1);

            Match m3 = new Match();
            m3.setId(UUID.randomUUID());
            m3.setPlayerOne(p1);
            m3.setPlayerTwo(p4);
            m3.setWinner(p4);

            Match m4 = new Match();
            m4.setId(UUID.randomUUID());
            m4.setPlayerOne(p1);
            m4.setPlayerTwo(p5);
            m4.setWinner(p1);

            Match m5 = new Match();
            m5.setId(UUID.randomUUID());
            m5.setPlayerOne(p2);
            m5.setPlayerTwo(p3);
            m5.setWinner(p3);

            matchDao.save(m1);
            matchDao.save(m2);
            matchDao.save(m3);
            matchDao.save(m4);
            matchDao.save(m5);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
