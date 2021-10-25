package servlets.User;

import Models.Flight;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.FlightService;

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

public class FlightsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Flight> flights = FlightService.getAllFlights();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(flights);
        resp.getWriter().print(json);
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String json = sc.useDelimiter("\\A").next();
        ObjectMapper mapper = new ObjectMapper();
        Flight payload = mapper.readValue(json, Flight.class);
    }
}

