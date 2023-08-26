package com.tennisscoreboard.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StartPageController implements MatchController {
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
            HttpServletResponse servletResponse) throws ServletException, IOException {
        executeGet(servletRequest, servletResponse);
    }
}
