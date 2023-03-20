package com.kharizma.tennisscoreboard.dbhandlers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBHandler {
    public static DBHandler instance;
    EntityManager entityManager;

    private DBHandler() {
    }

    public static DBHandler getInstance() {
        if(instance==null){
            instance = new DBHandler();
        }
        return instance;
    }

    public EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TennisScoreboard_PU");
        entityManager = emf.createEntityManager();
        return entityManager;
    }
}
