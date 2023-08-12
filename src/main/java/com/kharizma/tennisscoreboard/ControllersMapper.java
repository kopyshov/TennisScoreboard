package com.kharizma.tennisscoreboard;

import com.kharizma.tennisscoreboard.controllers.IController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllersMapper {
    IController service;
    private final Map<String, IController> services = new HashMap<>();

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

    public void addServices(String key, IController service) {
        services.put(key, service);
    }
}
