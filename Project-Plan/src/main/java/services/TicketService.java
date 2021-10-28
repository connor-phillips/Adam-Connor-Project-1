package services;

import Models.Flight;
import Models.Ticket;
import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class TicketService {
    private static SessionFactory sessionFactory;
    private static Session session;
    private static User user;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        TicketService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        TicketService.session = session;
    }

    public static void init() {
    }

    /**
     * This method populates the Ticket table with sample Ticket objects
     * Ten Ticket objects are created and persisted within the database
     */
    public static void populateTicketTable(List<User> users, List<Flight> flights) {
        Ticket ticket1 = new Ticket(users.get(1), flights.get(4), "Kayla", "Cleo", false, false);
        Ticket ticket2 = new Ticket(users.get(1), flights.get(4), "Ryley", "Saige", false, false);
        Ticket ticket3 = new Ticket(users.get(1), flights.get(4), "Terra", "Allison", false, false);
        Ticket ticket4 = new Ticket(users.get(1), flights.get(4), "Spring", "Ellington", false, false);
        Ticket ticket5 = new Ticket(users.get(1), flights.get(4), "Quincey", "Sandy", false, false);
        Ticket ticket6 = new Ticket(users.get(0), flights.get(2), "India", "Kiki", false, false);
        Ticket ticket7 = new Ticket(users.get(0), flights.get(2), "Kal", "Scott", false, false);
        Ticket ticket8 = new Ticket(users.get(0), flights.get(2), "Jami", "Sabrina", false, false);
        Ticket ticket9 = new Ticket(users.get(0), flights.get(2), "Davin", "Glenn", false, false);
        Ticket ticket10 = new Ticket(users.get(0), flights.get(2), "Carl", "Rikki", false, false);

        Transaction transaction = session.beginTransaction();
        session.save(ticket1);
        session.save(ticket2);
        session.save(ticket3);
        session.save(ticket4);
        session.save(ticket5);
        session.save(ticket6);
        session.save(ticket7);
        session.save(ticket8);
        session.save(ticket9);
        session.save(ticket10);
        transaction.commit();
    }

    /**
     * This method is used to create a new Ticket object and assign to
     * a newly created User
     * This method creates and persists a new Ticket object to the database
     * as well creates and persists a new User object to the database
     * @param flight_num
     * @param first_name
     * @param last_name
     */
    public static void purchaseTicket(Integer flight_num, String first_name, String last_name) {
        Ticket ticket = new Ticket();
        ticket.setFirst_name(first_name);
        ticket.setLast_name(last_name);
        ticket.setCancel(false);
        ticket.setCheckIn(false);
        ticket.setFlight(FlightService.getFlightByID(flight_num));
        ticket.setUser(UserService.createUser(first_name, last_name));
        Transaction t = session.beginTransaction();
        session.save(ticket);
        t.commit();
    }

    /**
     * This method is used to create a new Ticket object and assign to
     * an already persisted User
     * This method creates and persists a new Ticket object to the database
     * The User field of the Ticket is assigned to an existing User that is
     * grabbed from the database using a method within the UserService class
     * @param flight_num
     * @param first_name
     * @param last_name
     */
    public static void addTicket(Integer flight_num, String first_name, String last_name){
        Ticket ticket = new Ticket();
        ticket.setFirst_name(first_name);
        ticket.setLast_name(last_name);
        ticket.setCancel(false);
        ticket.setCheckIn(false);
        ticket.setFlight(FlightService.getFlightByID(flight_num));
        ticket.setUser(UserService.getUserByNames(first_name, last_name));
        Transaction t = session.beginTransaction();
        session.save(ticket);
        t.commit();
    }

    /**
     * This method grabs all the Ticket objects that are persisted within
     * the database
     * @return
     */
    public static List<Ticket> getAllTickets() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    /**
     * This method grabs all of the Ticket objects that are persisted within
     * the database and correspond to the specified unique identifier: User Id
     * @param user
     * @return
     */
    public static List<Ticket> getTicketsByID(User user){
        List<Ticket> tickets;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("user"), user));
        tickets = session.createQuery(query).getResultList();
        return tickets;
    }

    /**
     * This method updates the fields of an existing Ticket object
     * This method is used when Users choose to Check-In or cancel
     * their tickets
     * @param ticket
     */
    public static Ticket updateTicket(Ticket ticket){
        Transaction transaction = session.beginTransaction();
        Ticket updatedTicket = session.load(Ticket.class, ticket.getTicket_id());
        updatedTicket.setCheckIn(ticket.getCheckIn());
        updatedTicket.setCancel(ticket.getCancel());
        session.update(updatedTicket);
        transaction.commit();
        return updatedTicket;
    }
}
