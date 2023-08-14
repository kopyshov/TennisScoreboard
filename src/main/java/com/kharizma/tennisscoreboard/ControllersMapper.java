package com.kharizma.tennisscoreboard;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllersMapper {
    MatchController service;
    private final Map<String, MatchController> services = new HashMap<>();

    public void handleGetRequest(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) throws ServletException, IOException {
        String servletPath = servletRequest.getServletPath();
        service = services.get(servletPath);
        service.executeGet(servletRequest, servletResponse);
    }

    public void handlePostRequest(HttpServletRequest servletRequest,
                                 HttpServletResponse servletResponse) throws ServletException, IOException {
        String servletPath = servletRequest.getServletPath();
        service = services.get(servletPath);
        service.executePost(servletRequest, servletResponse);
    }

    public void addServices(String key, MatchController service) {
        services.put(key, service);
    }
}
