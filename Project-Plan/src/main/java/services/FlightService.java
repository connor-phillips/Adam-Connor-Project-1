package services;

import Models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FlightService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        FlightService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        FlightService.session = session;
    }

    public static void init() {
    }

    public static List<Flight> getAllFlights() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static Flight getFlightByID(Flight flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_num"), flight.getFlight_num()));
        return session.createQuery(query).getSingleResult();
    }

    public static List<Flight> getFlightByDetails(Flight flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.and(builder.equal(root.get("arrival_city"), flight.getArrival_city()),
                builder.equal(root.get("departure_city"), flight.getDeparture_city()),
                builder.equal(root.get("flight_date"), flight.getFlight_date())));
        return session.createQuery(query).getResultList();
    }

    public static void addFlight(Flight flight){
        Flight newFlight = new Flight();
        newFlight.setFlight_date(flight.getFlight_date());
        newFlight.setArrival_city(flight.getArrival_city());
        newFlight.setDeparture_city(flight.getDeparture_city());
        Transaction transaction = session.beginTransaction();
        session.save(newFlight);
        transaction.commit();
    }
}
