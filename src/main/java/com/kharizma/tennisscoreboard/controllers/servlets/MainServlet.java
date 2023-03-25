package com.kharizma.tennisscoreboard.controllers.servlets;

import com.kharizma.tennisscoreboard.controllers.MainController;
import com.kharizma.tennisscoreboard.services.CurrentMatchService;
import com.kharizma.tennisscoreboard.services.MatchScoreCalculationService;
import com.kharizma.tennisscoreboard.services.StartPageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "main", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    MainController mainController = new MainController();

    @Override
    public void init() {
        //сначала я делал if-else, но мне не понравилось. Выглядело не очень
        //Есть подозрение что это можно улучшить
        mainController.addServices("/", new StartPageService());
        mainController.addServices("/new-match", CurrentMatchService.getInstance());
        mainController.addServices("/match-score", new MatchScoreCalculationService());
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
