package com.kharizma.tennisscoreboard.controllers;

import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.players.Player;
import com.kharizma.tennisscoreboard.util.DatabaseHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.UUID;

public class StartPageController implements IController {
    @Override
    public void executeGet(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws ServletException, IOException
    {


        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void executePost(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse)
    {

    }
}
