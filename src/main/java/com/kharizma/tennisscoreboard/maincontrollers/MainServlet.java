package com.kharizma.tennisscoreboard.maincontrollers;

import com.kharizma.tennisscoreboard.controllers.StartPageController;
import com.kharizma.tennisscoreboard.matches.CurrentMatchController;
import com.kharizma.tennisscoreboard.matches.FinishedMatchesPersistenceController;
import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.matches.score.ScoreController;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
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
        mainController.addServices("/match-score", new ScoreController());
        mainController.addServices("/matches", new FinishedMatchesPersistenceController());
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

}
