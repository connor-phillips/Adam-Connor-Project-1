package services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Customers_FlightsService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        Customers_FlightsService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Customers_FlightsService.session = session;
    }

    public static void init() {
    }
}
