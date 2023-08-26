package com.tennisscoreboard;

import com.tennisscoreboard.controllers.StartPageController;
import com.tennisscoreboard.matches.CurrentMatchController;
import com.tennisscoreboard.matches.FinishedMatchesPersistenceController;
import com.tennisscoreboard.matches.score.ScoreController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "main", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    private final ControllersMapper controllersMapper = new ControllersMapper();

    @Override
    public void init() {
        controllersMapper.addServices("/", new StartPageController());
        controllersMapper.addServices("/new-match", CurrentMatchController.INSTANCE);
        controllersMapper.addServices("/match-score", new ScoreController());
        controllersMapper.addServices("/matches", new FinishedMatchesPersistenceController());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        controllersMapper.handleGetRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controllersMapper.handlePostRequest(req, resp);
    }

    public void destroy() {
    }

}
