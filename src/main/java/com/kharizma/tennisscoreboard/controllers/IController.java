package com.kharizma.tennisscoreboard.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IController {

    void executeGet(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws ServletException, IOException;

    void executePost(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws ServletException, IOException;
}
