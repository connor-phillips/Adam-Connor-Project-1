package services;

import Models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        CityService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        CityService.session = session;
    }

    public static void init() {
    }

    public static List<City> getAllCities() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<City> query = builder.createQuery(City.class);
        Root<City> root = query.from(City.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static void saveNewCity(City city){
        Session session = CityService.getSession();
        City newCity = new City();
        newCity.setCity(city.getCity());
        newCity.setState(city.getState());
        Transaction transaction = session.beginTransaction();
        session.save(newCity);
        transaction.commit();
    }
}
