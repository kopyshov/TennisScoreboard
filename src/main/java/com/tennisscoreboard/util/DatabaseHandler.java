package com.tennisscoreboard.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum DatabaseHandler {
    INSTANCE;
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
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
