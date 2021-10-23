package servlets;

import Models.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;


public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Session session = HibernateService.getSession();
        CityService.setSession(session);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateService.closeSession();
    }
}