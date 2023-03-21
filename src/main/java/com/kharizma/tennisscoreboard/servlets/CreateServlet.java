package com.kharizma.tennisscoreboard.servlets;

import com.kharizma.tennisscoreboard.services.CurrentMatchService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "new-match", value = "/new-match")
public class CreateServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String name1 = req.getParameter("name1");
        String name2 = req.getParameter("name2");

        CurrentMatchService matchService = CurrentMatchService.getInstance();
        matchService.insertPlayer(name1);
        matchService.insertPlayer(name2);
        matchService.createMatch();
    }

    @Override
    public void destroy() {
    }


}
