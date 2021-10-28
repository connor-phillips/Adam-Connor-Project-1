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

    /**
     * This method will populate the Flights table with sample Flight objects
     * Five sample Flights objects are created and persisted within the database
     */
    public static void populateFlightsTable(){
        List<Flight> flights = new LinkedList<>();
        Flight flight1 = new Flight("New York City", "Los Angeles", "October 30", "12 PM");
        Flight flight2 = new Flight("New York City", "Chicago", "November 3", "3 PM");
        Flight flight3 = new Flight("New York City", "Miami", "October 29", "6 PM");
        Flight flight4 = new Flight("New York City", "Houston", "October 26", "10 AM");
        Flight flight5 = new Flight("New York City", "Atlanta", "November 1", "1 PM");
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

    /**
     * This method grabs all the entries within the Flights table
     * The database is queried to grab all entries from the Flights table
     * that are Flight objects
     * After executing the query, the method returns a list of all the Flight
     * objects from the database
     */
    public static List<Flight> getAllFlights() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    /**
     * This method grabs a specific Flight object from the database
     * The method queries the database for Flight objects, and uses
     * the flight number as the unique parameter for the query
     * @param flight
     * Returns the Flight object which contains the provided flight
     * number
     */
    public static Flight getFlightByID(Flight flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_num"), flight.getFlight_num()));
        return session.createQuery(query).getSingleResult();
    }

    /**
     * This method grabs a specific Flight object from the database
     * The method queries the database for Flight objects, and uses
     * the flight number as the unique parameter for the query
     * @param flightNum
     * Returns the Flight object which contains the provided flight
     * number
     */
    public static Flight getFlightByID(int flightNum){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.equal(root.get("flight_num"), flightNum));
        return session.createQuery(query).getSingleResult();
    }

    /**
     * This method grabs a specific Flight object from the database
     * The method queries the database for Flight objects, and uses
     * the flight details - origin, destination, and date - to grab
     * the proper Flight object
     * @param flight
     * Returns the Flight object which contains the provided flight
     * number
     */
    public static List<Flight> getFlightByDetails(Flight flight){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Flight> query = builder.createQuery(Flight.class);
        Root<Flight> root = query.from(Flight.class);
        query.select(root).where(builder.and(builder.equal(root.get("origin"), flight.getDestination()),
                builder.equal(root.get("destination"), flight.getOrigin()),
                builder.equal(root.get("date"), flight.getDate())));
        return session.createQuery(query).getResultList();
    }

    /**
     * This method persists a new Flight object in the database
     * After being passed the flight details - origin, destination,
     * date, and time, the method queries the database to find a Flight
     * object with those given details
     * If a Flight object is returned, the user is notifies that the object
     * already exists
     * Otherwise, the given Flight object is created and persisted into the
     * database
     * @param flight
     */
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
            session.save(flight);
            transaction.commit();
            alert = "New Flight Created";
        } else {
            alert = "This Flight already exists";
        }
        return alert;
    }
}
