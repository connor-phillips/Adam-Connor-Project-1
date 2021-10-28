package services;

import Models.Admin;
import Models.Flight;
import Models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.FileLogger;

import javax.persistence.NoResultException;
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

    /**
     * This method populates the User table with sample User objects
     * Two User objects are created and persisted within the database
     * @param flights
     */
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
    }

    /**
     * This method queries the database and grabs all the User objects
     * that are persisted within the User table
     */
    public static List<User> getAllUsers() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    /**
     * This method queries the database and grabs the User object
     * that corresponds to the unique identifier - User ID
     * @param user
     */
    public static User getUserByID(User user){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        try {
            query.select(root).where(builder.equal(root.get("user_id"), user.getUser_id()));
        } catch (NoResultException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
        return session.createQuery(query).getSingleResult();
    }

    /**
     * This method queries the database for User objects and grabs the User
     * object that corresponds to the provided parameters - first name and
     * last name
     * @param first_name
     * @param last_name
     */
    public static User getUserByNames(String first_name, String last_name){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.and(builder.equal(root.get("first_name"), first_name), builder.equal(root.get("last_name"), last_name)));
        return session.createQuery(query).getSingleResult();
    }

    /**
     * This method queries the database for User objects and grabs the User
     * object that corresponds to the provided parameters - first name and
     * last name
     * If a User object is returned, the User object will be loaded from the database
     * Otherwise,the created User object will be persisted in the database
     * @param first_name
     * @param last_name
     */
    public static User createUser(String first_name, String last_name){
        List<User> userCheck;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.and(builder.equal(root.get("first_name"), first_name), builder.equal(root.get("last_name"), last_name)));
        userCheck = session.createQuery(query).getResultList();
        User user = new User(first_name, last_name);
        Transaction transaction = session.beginTransaction();
        if (userCheck.size() == 0){
            session.save(user);
            transaction.commit();
        } else {
            session.load(User.class, first_name);
        }
        return user;
    }
}
