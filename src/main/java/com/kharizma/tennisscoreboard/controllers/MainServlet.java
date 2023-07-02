package com.kharizma.tennisscoreboard.controllers;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import com.kharizma.tennisscoreboard.services.CurrentMatchController;
import com.kharizma.tennisscoreboard.services.FinishedMatchesPersistenceController;
import com.kharizma.tennisscoreboard.services.MatchScoreController;
import com.kharizma.tennisscoreboard.services.StartPageController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "main", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    private final MainController mainController = new MainController();

    @Override
    public void init() {
        //сначала я делал if-else, но мне не понравилось. Выглядело не очень
        //Есть подозрение что это можно улучшить
        mainController.addServices("/", new StartPageController());
        mainController.addServices("/new-match", CurrentMatchController.getInstance());
        mainController.addServices("/match-score", new MatchScoreController());
        mainController.addServices("/matches", new FinishedMatchesPersistenceController());

        //Добавляет тестовые матчи
        addSomeMatches();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        mainController.handleGetRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainController.handlePostRequest(req, resp);
    }

    public void destroy() {
    }

    public void addSomeMatches() {
        try(Session session = DBHandler.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Player p1 = new Player();
            p1.generateId();
            p1.setName("Петров");
            session.merge(p1);
            session.flush();

            Player p2 = new Player();
            p2.generateId();
            p2.setName("Иванов");
            session.merge(p2);
            session.flush();

            Player p3 = new Player();
            p3.generateId();
            p3.setName("Сидоров");
            session.merge(p3);
            session.flush();

            Player p4 = new Player();
            p4.generateId();
            p4.setName("Ушаков");
            session.merge(p4);
            session.flush();

            Player p5 = new Player();
            p5.generateId();
            p5.setName("Баранов");
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
