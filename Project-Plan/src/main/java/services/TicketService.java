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
import java.util.List;

public class TicketService {
    private static SessionFactory sessionFactory;
    private static Session session;

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
    public static void addTicket(Integer flight_num, String first_name, String last_name){
        Ticket ticket = new Ticket();
        ticket.setFirst_name(first_name);
        ticket.setLast_name(last_name);
        ticket.setCancel(false);
        ticket.setCheckIn(false);
        ticket.setFlight(FlightService.getFlightByID(flight_num));
        ticket.setUser(UserService.getCustomerByNames(first_name, last_name));
        Transaction t = session.beginTransaction();
        session.save(ticket);
        t.commit();
    }

    public static List<Ticket> getAllTickets() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static Ticket getTicketByID(Ticket ticket){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("ticket_id"), ticket.getTicket_id()));
        return session.createQuery(query).getSingleResult();
    }
}
