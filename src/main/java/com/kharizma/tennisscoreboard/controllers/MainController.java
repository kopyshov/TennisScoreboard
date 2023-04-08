package com.kharizma.tennisscoreboard.controllers;

import com.kharizma.tennisscoreboard.services.IService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainController {
    IService service;
    private final Map<String, IService> services = new HashMap<>();

    public void handleGetRequest(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws ServletException, IOException {
        String servletPath = servletRequest.getServletPath();
        service = services.get(servletPath);
        System.out.println("-------PATH--------");
        System.out.println(servletPath);
        System.out.println("-------------------");
        service.executeGet(servletRequest, servletResponse);
    }

    public void handlePostRequest(HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) throws ServletException, IOException {
        String servletPath = servletRequest.getServletPath();
        service = services.get(servletPath);
        service.executePost(servletRequest, servletResponse);
    }

    public void addServices(String key, IService service) {
        services.put(key, service);
    }
}
