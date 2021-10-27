package servlets.Admin;

import Models.Flight;
import Models.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.FlightService;
import services.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FlightServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        InputStream requestBody = req.getInputStream();
//        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
//        String jsonText = sc.next();
        String payload = req.getHeader("Payload-Type");
        switch(payload) {
            case "schedule-flight":
                InputStream scheduleRequest = req.getInputStream();
                Scanner scNew = new Scanner(scheduleRequest, StandardCharsets.UTF_8.name());
                String jsonText = scNew.useDelimiter("\\A").next();
                ObjectMapper mapper = new ObjectMapper();
                Flight newFlight = mapper.readValue(jsonText, Flight.class);
                String response = FlightService.addFlight(newFlight);

                System.out.println("DEBUG - Message: " + response);
//                Ticket updatedTicket = TicketService.updateTicket(ticket);
//                System.out.println("DEBUG - Ticket: " + updatedTicket.getTicket_id() + " " + updatedTicket.getLast_name() + " " + updatedTicket.getFlight());
//                String jsonString = mapper.writeValueAsString(updatedTicket);
//                System.out.println("DEBUG - Updated Ticket: " + jsonString);
                resp.getWriter().write(mapper.writeValueAsString(response));
                resp.setContentType("application/json");
                break;
            case "cancel-flight":
                InputStream cancelRequest = req.getInputStream();
                Scanner scCancel = new Scanner(cancelRequest, StandardCharsets.UTF_8.name());
                String jsonCancel = scCancel.useDelimiter("\\A").next();
                ObjectMapper mapperCancel = new ObjectMapper();
                Flight toCancel = mapperCancel.readValue(jsonCancel, Flight.class);
                String notification = FlightService.cancelFlight(toCancel);

                System.out.println("DEBUG - Message: " + notification);
//                Ticket updatedTicket = TicketService.updateTicket(ticket);
//                System.out.println("DEBUG - Ticket: " + updatedTicket.getTicket_id() + " " + updatedTicket.getLast_name() + " " + updatedTicket.getFlight());
//                String jsonString = mapper.writeValueAsString(updatedTicket);
//                System.out.println("DEBUG - Updated Ticket: " + jsonString);
                resp.getWriter().write(mapperCancel.writeValueAsString(notification));
                resp.setContentType("application/json");
                break;
        }
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
