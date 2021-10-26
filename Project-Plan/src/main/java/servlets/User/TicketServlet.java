package servlets.User;

import Models.Ticket;
import Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.TicketService;
import services.UserService;

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

public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int ticketId = Integer.parseInt(req.getParameter("ticket_id"));
//        boolean checkIn = Boolean.parseBoolean(req.getParameter("checkIn"));
//        boolean cancel = Boolean.parseBoolean(req.getParameter("cancel"));
        InputStream requestBody = req.getInputStream();
        Scanner sc = new Scanner(requestBody, StandardCharsets.UTF_8.name());
        String jsonText = sc.useDelimiter("\\A").next();
        ObjectMapper mapper =  new ObjectMapper();
        Ticket ticket = mapper.readValue(jsonText, Ticket.class);

        System.out.println("DEBUG - ticketId: " + ticket.getTicket_id());
        System.out.println("DEBUG - cancel: " + ticket.getCancel());
        System.out.println("DEBUG - checkIn: " + ticket.getCheckIn());
        Ticket updatedTicket = TicketService.updateTicket(ticket);
        System.out.println("DEBUG - Ticket: " + ticket.getTicket_id() + " " + ticket.getLast_name() + " " + ticket.getFlight());
        String jsonString = mapper.writeValueAsString(updatedTicket);
        System.out.println("DEBUG - Updated Ticket: " + jsonString);
        resp.getWriter().write(mapper.writeValueAsString(updatedTicket));
        resp.setContentType("application/json");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
