package com.kharizma.tennisscoreboard.dbhandlers;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBHandler {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private DBHandler() {
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
