package servlets.User;

import Models.Ticket;
import Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.TicketService;
import services.UserService;
import utils.FileLogger;

import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * This is the Ticket Servlet. This servlet takes a POST request and updates the user's ticket based upon whether
 * they want to either check in to their flight or cancel.
 */

public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
//        int ticketId = Integer.parseInt(req.getParameter("ticket_id"));
//        boolean checkIn = Boolean.parseBoolean(req.getParameter("checkIn"));
//        boolean cancel = Boolean.parseBoolean(req.getParameter("cancel"));
        try {
            InputStream requestBody = req.getInputStream();
            Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
            String jsonText = sc.useDelimiter("\\A").next();
            ObjectMapper mapper = new ObjectMapper();
            Ticket ticket = mapper.readValue(jsonText, Ticket.class);
            //Update the ticket based upon the request
            Ticket updatedTicket = TicketService.updateTicket(ticket);
            String jsonString = mapper.writeValueAsString(updatedTicket);
//            PrintWriter out = resp.getWriter();
//            int id = updatedTicket.getTicket_id();
//            out.println(id);
            resp.getWriter().write(mapper.writeValueAsString(updatedTicket));
            resp.setContentType("application/json");
        } catch (NoResultException | IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
