package servlets.Admin;

import Models.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.FlightService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ScheduleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Flight flight = new Flight();
        flight.setDestination(req.getParameter("origin"));
        flight.setOrigin(req.getParameter("destination"));
        flight.setDate(req.getParameter("date"));
        String hour = req.getParameter("hour");
        String period = req.getParameter("period");
        String time = hour + " " + period;
        flight.setTime(time);

        String alert;
        alert = FlightService.addFlight(flight);

        PrintWriter out = resp.getWriter();
        out.println(alert);
    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Flight newFlight = new Flight();
//        newFlight.setOrigin(req.getParameter("departure"));
//        newFlight.setDestination(req.getParameter("arrival"));
//        newFlight.setDate(req.getParameter("date"));
//        FlightService.addFlight(newFlight);
//    }
}
