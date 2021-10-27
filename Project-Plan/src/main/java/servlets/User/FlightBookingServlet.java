package servlets.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.*;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try{
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String json = sc.useDelimiter("\\A").next();
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            Ticket ticket = mapper.readValue(json, Ticket.class);
            TicketService.purchaseTicket(ticket);
            resp.setStatus(202);

        } catch(IOException e){
            FileLogger.getFileLogger().writeLog(e.toString(), 4);
        }
    }


}
