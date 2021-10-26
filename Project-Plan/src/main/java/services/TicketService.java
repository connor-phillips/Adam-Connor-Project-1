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

    public static void purchaseTicket(Ticket ticket) {
        Ticket pTicket = session.get(Ticket.class, ticket.getTicket_id());
        CriteriaBuilder c = session.getCriteriaBuilder();

        CriteriaQuery<Flight> flight = c.createQuery(Flight.class);
        Root<Flight> root = flight.from(Flight.class);
        flight.where(c.equal(root.get("flightNum"), ticket.getFlight()));
        List<Flight> l = session.createQuery(flight).getResultList();
        Flight f = session.get(Flight.class, l.get(0).getFlight_num());
        CriteriaQuery<User> u = c.createQuery(User.class);
        Root<User> uRoot = u.from(User.class);
        u.where(c.equal(uRoot.get("firstName"), ticket.getFirst_name()));
        u.where(c.equal(uRoot.get("lastName"), ticket.getLast_name()));
        List<User> uL = session.createQuery(u).getResultList();
        User user = session.get(User.class, uL.get(0).getFirst_name());

        session.flush();

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
