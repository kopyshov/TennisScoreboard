package com.tennisscoreboard.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseHandler {
    private static SessionFactory sessionFactory;
    private DatabaseHandler() {
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
