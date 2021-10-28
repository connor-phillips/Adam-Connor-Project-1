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

    public static void populateCitiesTable(){
        City city1 = new City("New York City", "NY");
        City city2 = new City("Chicago", "IL");
        City city3 = new City("Miami", "FL");
        City city4 = new City("Los Angeles", "CA");
        City city5 = new City("Providence", "RI");

        Transaction transaction = session.beginTransaction();
        session.save(city1);
        session.save(city2);
        session.save(city3);
        session.save(city4);
        session.save(city5);
        transaction.commit();

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
