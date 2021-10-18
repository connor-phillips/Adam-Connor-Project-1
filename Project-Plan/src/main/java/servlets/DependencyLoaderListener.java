package servlets;

import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import services.*;
import utils.ConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;


public class DependencyLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Connection conn = ConnectionManager.getConnection();
        FlightService.init();
        TicketService.init();
        CustomerService.init();
        AdminService.init();
        CityService.init();
        Customers_FlightsService.init();

        //Hibernate config and context initialization
        Configuration config = new Configuration();
        config.addAnnotatedClass(Flight.class);
        config.addAnnotatedClass(Ticket.class);
        config.addAnnotatedClass(Customer.class);
        config.addAnnotatedClass(City.class);
        config.addAnnotatedClass(Admin.class);
        config.addAnnotatedClass(Customers_Flights.class);
        FlightService.setSessionFactory(config.buildSessionFactory());
        FlightService.setSession(FlightService.getSessionFactory().openSession());
        TicketService.setSessionFactory(config.buildSessionFactory());
        TicketService.setSession(FlightService.getSessionFactory().openSession());
        CustomerService.setSessionFactory(config.buildSessionFactory());
        CustomerService.setSession(FlightService.getSessionFactory().openSession());
        AdminService.setSessionFactory(config.buildSessionFactory());
        AdminService.setSession(FlightService.getSessionFactory().openSession());
        CityService.setSessionFactory(config.buildSessionFactory());
        CityService.setSession(FlightService.getSessionFactory().openSession());
        Customers_FlightsService.setSessionFactory(config.buildSessionFactory());
        Customers_FlightsService.setSession(FlightService.getSessionFactory().openSession());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        FlightService.getSession().close();
        TicketService.getSession().close();
        CustomerService.getSession().close();
        AdminService.getSession().close();
        CityService.getSession().close();
        Customers_FlightsService.getSession().close();
    }
}