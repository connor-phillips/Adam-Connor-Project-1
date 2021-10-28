package servlets.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.*;
import org.json.JSONObject;
import services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.UserService;
import utils.FileLogger;


import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is the Flight Booking Servlet. This servlet creates a new ticket for the user after they enter their
 * first name, last name, and the flight number for the flight they are getting tickets for.
 */

public class FlightBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Ticket> Tickets = TicketService.getAllTickets();
            String json = mapper.writeValueAsString(Tickets);
            resp.getWriter().write(json);
            resp.setContentType("application/json");
            resp.setStatus(200);
        } catch(IOException e) {
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String json = sc.next();
            //Take the json and create a new JSONObject with it
            JSONObject jsonObject = new JSONObject(json);
            //Parse the json to retrieve the parameters
            int flight_num = jsonObject.getInt("flight_num");
            String first_name = jsonObject.getString("first_name");
            String last_name = jsonObject.getString("last_name");
            String header = req.getHeader("Payload-Type");
            //Based upon the header either create a new user if it is the user's first time buying a ticket
            //or add another ticket if the user already has a ticket. This allows them to purchase multiple
            //tickets if they desire.
            switch (header) {
                case "newUser":
                    TicketService.purchaseTicket(flight_num, first_name, last_name);
                    break;
                case "newTicket":
                    TicketService.addTicket(flight_num, first_name, last_name);
                    break;
            }
            User user = UserService.getUserByNames(first_name, last_name);
            PrintWriter out = resp.getWriter();
            int id = user.getUser_id();
            //Send back the user's new user id so they can use it to check in or cancel.
            out.println(id);
        } catch(IOException e) {
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }

    }


}
