package services;

import Models.Admin;
import Models.Flight;
import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public class UserService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        UserService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        UserService.session = session;
    }

    public static void init() {
    }

    public static void populateUserTable(List<Flight> flights){
        List<User> users = new LinkedList<>();
        User user1 = new User("Jami", "Sabrina", flights.get(2));
        User user2 = new User("Spring", "Ellington", flights.get(4));
        users.add(user1);
        users.add(user2);

        Transaction transaction = session.beginTransaction();
        session.save(user1);
        session.save(user2);
        transaction.commit();

        TicketService.populateTicketTable(users, flights);

        //Uer gets entered once they purchase a ticket
        //Within the User table:
        //User ID
        //First Name
        //Last Name
        //Flight Num
    }

    public static List<User> getAllUsers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static User getUserByID(User user){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get("user_id"), user.getUser_id()));
        return session.createQuery(query).getSingleResult();
    }
}
