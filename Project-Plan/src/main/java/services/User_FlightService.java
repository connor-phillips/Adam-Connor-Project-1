package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class User_FlightService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        User_FlightService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        User_FlightService.session = session;
    }

    public static void init() {
    }
}
