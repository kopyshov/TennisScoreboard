package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.UUID;

public class StartPageService implements IService {
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
