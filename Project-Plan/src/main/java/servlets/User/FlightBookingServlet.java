package servlets.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.*;
import org.json.JSONObject;
import services.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.FileLogger;


import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;


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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String json = sc.next();
        JSONObject jsonObject = new JSONObject(json);
        int flight_num = jsonObject.getInt("flight_num");
        String first_name = jsonObject.getString("first_name");
        String last_name = jsonObject.getString("last_name");
        //Ticket ticket = mapper.readValue(json, Ticket.class);
        //TicketService.purchaseTicket(ticket);
        resp.setStatus(202);
        String header = req.getHeader("Payload-Type");
        //int flight_num = Integer.parseInt(req.getParameter("flight_num"));
        //String first_name = req.getParameter("first_name");
        //String last_name = req.getParameter("last_name");
        switch(header){
            case "newUser":
                TicketService.purchaseTicket(flight_num, first_name, last_name);
                break;
            case "newTicket":
                TicketService.addTicket(flight_num, first_name, last_name);
                break;
        }


    }


}
