package com.kharizma.tennisscoreboard;

import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

@WebListener
public class StartApp implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public StartApp() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        //Добавляет тестовые матчи
        addSomeMatches();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }

    public void addSomeMatches() {
        try(Session session = DatabaseHandler.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Player p1 = new Player();
            p1.generateId();
            p1.setName("А. Петров");
            session.merge(p1);
            session.flush();

            Player p2 = new Player();
            p2.generateId();
            p2.setName("И. Иванов");
            session.merge(p2);
            session.flush();

            Player p3 = new Player();
            p3.generateId();
            p3.setName("С. Сидоров");
            session.merge(p3);
            session.flush();

            Player p4 = new Player();
            p4.generateId();
            p4.setName("У. Ушаков");
            session.merge(p4);
            session.flush();

            Player p5 = new Player();
            p5.generateId();
            p5.setName("Б. Баранов");
            session.merge(p5);
            session.flush();


            Match m1 = new Match(UUID.randomUUID());
            m1.setPlayerOne(p1);
            m1.setPlayerTwo(p2);
            m1.setWinner(p2);
            session.merge(m1);
            session.flush();

            Match m2 = new Match(UUID.randomUUID());
            m2.setPlayerOne(p1);
            m2.setPlayerTwo(p3);
            m2.setWinner(p1);
            session.merge(m2);
            session.flush();

            Match m3 = new Match(UUID.randomUUID());
            m3.setPlayerOne(p1);
            m3.setPlayerTwo(p4);
            m3.setWinner(p4);
            session.merge(m3);
            session.flush();

            Match m4 = new Match(UUID.randomUUID());
            m4.setPlayerOne(p1);
            m4.setPlayerTwo(p5);
            m4.setWinner(p1);
            session.merge(m4);
            session.flush();

            Match m5 = new Match(UUID.randomUUID());
            m5.setPlayerOne(p2);
            m5.setPlayerTwo(p3);
            m5.setWinner(p3);
            session.merge(m5);
            session.flush();


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
