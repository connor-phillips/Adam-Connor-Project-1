package services;

import Models.Admin;
import Models.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminService {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        AdminService.sessionFactory = sessionFactory;
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        AdminService.session = session;
    }

    public static void init(){
    }

    public static void populateAdminTable(){
        Flight flight1 = new Flight("New York City", "Los Angeles", "October 30", "12 P.M.");
        Flight flight2 = new Flight("New York City", "Chicago", "November 3", "3 P.M.");
        Flight flight3 = new Flight("New York City", "Miami", "October 29", "6 P.M.");
        Flight flight4 = new Flight("New York City", "Houston", "October 26", "10 A.M.");
        Flight flight5 = new Flight("New York City", "Atlanta", "November 1", "1 P.M.");

        Transaction transaction = session.beginTransaction();
        session.save(flight1);
        session.save(flight2);
        session.save(flight3);
        session.save(flight4);
        session.save(flight5);
        transaction.commit();
    }

    public static List<Admin> getAllAdmins() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    public static Admin authenticate(Admin admin){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root).where(builder.and(builder.equal(root.get("username"), admin.getUsername()), builder.equal(root.get("password"), admin.getPassword())));
        return session.createQuery(query).getSingleResult();
    }

    public static String registerAdmin(Admin admin){
        String alert;
        List<Admin> adminCheck;
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root).where(builder.equal(root.get("username"), admin.getUsername()));
        adminCheck = session.createQuery(query).getResultList();
        if (adminCheck.size() == 0){
            Transaction transaction = session.beginTransaction();
            session.save(admin);
            transaction.commit();
            alert = "New Admin Created";
        } else {
            alert = "This Admin already exists";
        }
//        alert = "The Method Works";
        return alert;
//        Admin newAdmin = new Admin();
//        newAdmin.setFirst_name(admin.getFirst_name());
//        newAdmin.setLast_name(admin.getLast_name());
//        newAdmin.setUsername(admin.getUsername());
//        newAdmin.setPassword(admin.getPassword());
//        Transaction transaction = session.beginTransaction();
//        session.save(newAdmin);
//        transaction.commit();

    }
}
