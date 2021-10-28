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

    /**
     * This method is used to populate the Admin Table with sample entries
     * Two admin entries are created and persisted within the database
     */
    public static void populateAdminTable(){
        Admin admin1 = new Admin("adixon", "123password", "Adam", "Dixon");
        Admin admin2 = new Admin("cphillips", "456password", "Connor", "Phillips");

        Transaction transaction = session.beginTransaction();
        session.save(admin1);
        session.save(admin2);
        transaction.commit();
    }

    /**
     * This queries the database and returns a list of all the Admin
     * entries that exist within the Admin table
     * @return
     */
    public static List<Admin> getAllAdmins() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root);
        return session.createQuery(query).getResultList();
    }

    /**
     * This method verifies that credentials which an Admin inputs
     * into the web page correspond to an entry in the database
     * The database is queried using the first and last name fields
     * that exist within the Admin object
     * @param admin
     * @return
     */
    public static Admin authenticate(Admin admin){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
        Root<Admin> root = query.from(Admin.class);
        query.select(root).where(builder.and(builder.equal(root.get("username"), admin.getUsername()), builder.equal(root.get("password"), admin.getPassword())));
        return session.createQuery(query).getSingleResult();
    }

    /**
     * This method is used to create and persist a new Admin object
     * into the database
     * The method queries the database to see if an Admin object with
     * the given username already exists
     * If the entry already exists, it will notify the user that the
     * Admin already exists
     * Otherwise, the Admin object created and persisted within the database
     * @param admin
     * @return
     */
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
        return alert;
    }
}
