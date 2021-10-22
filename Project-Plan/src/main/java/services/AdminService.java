package services;

import Models.Admin;
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

    public static void registerAdmin(Admin admin){
        Admin newAdmin = new Admin();
        newAdmin.setFirst_name(admin.getFirst_name());
        newAdmin.setLast_name(admin.getLast_name());
        newAdmin.setUsername(admin.getUsername());
        newAdmin.setPassword(admin.getPassword());
        Transaction transaction = session.beginTransaction();
        session.save(newAdmin);
        transaction.commit();

    }
}
