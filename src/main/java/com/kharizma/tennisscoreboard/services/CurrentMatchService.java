package com.kharizma.tennisscoreboard.services;

import com.kharizma.tennisscoreboard.dbhandlers.DBHandler;
import com.kharizma.tennisscoreboard.models.Match;
import com.kharizma.tennisscoreboard.models.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CurrentMatchService {

    private static CurrentMatchService instance;
    private final SessionFactory sessionFactory;
    private static Player player1;
    private static Player player2;
    //private final int[] score = new int[2];

    private CurrentMatchService() {
        sessionFactory = DBHandler.getSessionFactory();
    }

    public static CurrentMatchService getInstance() {
        if(instance == null) {
            instance = new CurrentMatchService();
        }
        return instance;
    }

    public void insertPlayer(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Player player = new Player();
        player.setName(name);
        session.persist(player);
        session.getTransaction().commit();
        session.close();

        addPlayersToCurrentMatch(player);
    }

    private void addPlayersToCurrentMatch(Player player) {
        if (player1 != null) {
            setPlayer1(player);
        } else {
            setPlayer2(player);
        }
    }

    public void createMatch() {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Match currentMatch = new Match();
        currentMatch.setPlayerOne(player1);
        currentMatch.setPlayerTwo(player2);
        session.getTransaction().commit();
        session.close();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        CurrentMatchService.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        CurrentMatchService.player2 = player2;
    }
}
