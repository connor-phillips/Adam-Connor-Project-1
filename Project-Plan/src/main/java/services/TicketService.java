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

    public static void populateTicketTable(List<User> users, List<Flight> flights){
        Ticket ticket1 = new Ticket(users.get(1), flights.get(4),"Kayla", "Cleo", false, false);
        Ticket ticket2 = new Ticket(users.get(1), flights.get(4),"Ryley", "Saige", false, false);
        Ticket ticket3 = new Ticket(users.get(1), flights.get(4),"Terra", "Allison", false, false);
        Ticket ticket4 = new Ticket(users.get(1), flights.get(4), "Spring", "Ellington", false, false);
        Ticket ticket5 = new Ticket(users.get(1), flights.get(4),"Quincey", "Sandy", false, false);
        Ticket ticket6 = new Ticket(users.get(0), flights.get(2),"India", "Kiki", false, false);
        Ticket ticket7 = new Ticket(users.get(0), flights.get(2),"Kal", "scott", false, false);
        Ticket ticket8 = new Ticket(users.get(0), flights.get(2),"Jami", "Sabrina", false, false);
        Ticket ticket9 = new Ticket(users.get(0), flights.get(2),"Davin", "Glenn", false, false);
        Ticket ticket10 = new Ticket(users.get(0), flights.get(2),"Carl", "Rikki", false, false);

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

    public static List<Ticket> getAllTickets() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static List<Ticket> getTicketsByID(User user){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ticket> query = builder.createQuery(Ticket.class);
        Root<Ticket> root = query.from(Ticket.class);
        query.select(root).where(builder.equal(root.get("user"), user));
        return session.createQuery(query).getResultList();
    }
}
