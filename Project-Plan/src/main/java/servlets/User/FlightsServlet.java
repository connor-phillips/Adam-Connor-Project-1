package servlets.User;

import Models.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.FlightService;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the user Flights Servlet. This servlet searches the flights table for all the currently scheduled flights
 * and sends it back to the webpage to be put into a table and displayed to the user when they are searching
 * for flights.
 */

public class FlightsServlet extends HttpServlet {

    //Get method to retrieve the flights from the flight table and send them back as an HTTP response
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            List<Flight> flights = FlightService.getAllFlights();
            ObjectMapper mapper = new ObjectMapper();
            resp.getWriter().write(mapper.writeValueAsString(flights));
            resp.setContentType("application/json");
            resp.setStatus(200);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String json = sc.useDelimiter("\\A").next();
            ObjectMapper mapper = new ObjectMapper();
            Flight payload = mapper.readValue(json, Flight.class);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }
}

