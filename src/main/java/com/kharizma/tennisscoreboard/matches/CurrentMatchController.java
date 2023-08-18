package com.kharizma.tennisscoreboard.matches;

import com.kharizma.tennisscoreboard.controllers.MatchController;
import com.kharizma.tennisscoreboard.matches.score.MatchScore;
import com.kharizma.tennisscoreboard.players.Player;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CurrentMatchController implements MatchController {
    public static CurrentMatchController instance;
    private String message;
    private final Map<UUID, Match> matches = new ConcurrentHashMap<>();
    private CurrentMatchController() {
    }
    public static CurrentMatchController getInstance() {
        if(instance == null) {
            instance = new CurrentMatchController();
        }
        return instance;
    }
    @Override
    public void executeGet( HttpServletRequest servletRequest,
                            HttpServletResponse servletResponse) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }
    @Override
    public void executePost(HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws IOException, ServletException {

        Player playerOne = new Player();
        String name1 = servletRequest.getParameter("name1");
        Player playerTwo = new Player();
        String name2 = servletRequest.getParameter("name2");
        if(!isInputNamesValid(name1, name2)){
            sendErrorMessage(servletRequest, servletResponse);
        }
        playerOne.setName(name1);
        playerTwo.setName(name2);
        playerOne.setCurrentId(UUID.randomUUID());
        playerTwo.setCurrentId(UUID.randomUUID());

        Match currentMatch = new Match();
        UUID uuid = UUID.randomUUID();
        currentMatch.setId(uuid);
        currentMatch.setPlayerOne(playerOne);
        currentMatch.setPlayerTwo(playerTwo);
        currentMatch.setMatchScore(new MatchScore());

        matches.put(currentMatch.getId(), currentMatch);
        servletResponse.sendRedirect(servletRequest.getContextPath() + "/match-score?uuid=" + uuid);
    }

    private void sendErrorMessage(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
        String name1 = servletRequest.getParameter("name1");
        String name2 = servletRequest.getParameter("name2");
        servletRequest.setAttribute("message", message);
        servletRequest.setAttribute("name1", name1);
        servletRequest.setAttribute("name2", name2);
        RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/create.jsp");
        requestDispatcher.forward(servletRequest, servletResponse);
    }

    private boolean isInputNamesValid(String name1, String name2) {
        if (name1.isEmpty() || name2.isEmpty()) {
            message = "Не знаю как тебе удалось но поле Игрок не должно быть пустым";
            return false;
        }
        if (name1.isBlank() || name2.isBlank()) {
            message = "Не знаю как тебе удалось но поле Игрок не должно быть пустым";
            return false;
        }
        if (name1.equals(name2)) {
            message = "Игрок играет сам с собой? Имена должны быть разными";
            return false;
        }
        return true;
    }

    public Match getMatch(UUID uuid) {
        return matches.get(uuid);
    }
    public void removeMatch(UUID uuid) {
        matches.remove(uuid);
    }
}
