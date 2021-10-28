package servlets;

import org.hibernate.Session;
import services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Dependency Loader Listener to create and populate our tables in hibernate
 */

public class DependencyLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Session session = HibernateService.getSession();
//        CityService.setSession(session);
        AdminService.setSession(session);
        TicketService.setSession(session);
        UserService.setSession(session);
        FlightService.setSession(session);

        //Populates the Flights, Users, and Tickets Tables
        FlightService.populateFlightsTable();

        //Populates the Admin Table
        AdminService.populateAdminTable();
//        UserService.populateUserTable();
//        User_FlightService.setSession(session);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateService.closeSession();
    }
}