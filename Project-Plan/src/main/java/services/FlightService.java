package services;

import Models.Admin;
import Models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
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

    public static void populateFlightsTable(){
        List<Flight> flights = new LinkedList<>();
        Flight flight1 = new Flight("New York City", "Los Angeles", "October 30", "12 PM", false);
        Flight flight2 = new Flight("New York City", "Chicago", "November 3", "3 PM", false);
        Flight flight3 = new Flight("New York City", "Miami", "October 29", "6 PM", false);
        Flight flight4 = new Flight("New York City", "Houston", "October 26", "10 AM", false);
        Flight flight5 = new Flight("New York City", "Atlanta", "November 1", "1 PM", false);
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);

        Transaction transaction = session.beginTransaction();
        session.save(flight1);
        session.save(flight2);
        session.save(flight3);
        session.save(flight4);
        session.save(flight5);
        transaction.commit();

        UserService.populateUserTable(flights);
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

    public static Flight getFlightByID(int flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_num"), flight));
        return session.createQuery(query).getSingleResult();
    }

    public static List<Flight> getFlightByDetails(Flight flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.and(builder.equal(root.get("arrival_city"), flight.getDestination()),
                builder.equal(root.get("departure_city"), flight.getOrigin()),
                builder.equal(root.get("flight_date"), flight.getDate())));
        return session.createQuery(query).getResultList();
    }

    public static String addFlight(Flight flight){
        String alert;
        List<Flight> flightsCheck;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.and(builder.equal(root.get("origin"), flight.getOrigin()),
                builder.equal(root.get("destination"), flight.getDestination()),
                builder.equal(root.get("date"), flight.getDate()),
                builder.equal(root.get("time"), flight.getTime())));
        flightsCheck = session.createQuery(query).getResultList();
        if (flightsCheck.size() == 0){
            Transaction transaction = session.beginTransaction();
            flight.setCancelled(false);
            session.save(flight);
            transaction.commit();
            alert = "New Flight Created";
        } else {
            alert = "This Flight already exists";
        }
        return alert;
    }

    public static String cancelFlight(Flight flight){
        String alert;
        List<Flight> checkFlight;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_num"), flight.getFlight_num()));
        checkFlight = session.createQuery(query).getResultList();
        if (checkFlight.size() == 0) {
            alert = "This flight does not exist";
        } else {
            Transaction transaction = session.beginTransaction();
            Flight updatedFlight = session.load(Flight.class, flight.getFlight_num());
            updatedFlight.setCancelled(flight.getCancelled());
            session.save(updatedFlight);
            transaction.commit();
            alert = "Flight #" + updatedFlight.getFlight_num() + " has been cancelled";
        }
        return alert;
    }
}
