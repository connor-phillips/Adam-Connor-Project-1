package servlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TicketServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int ticketId = Integer.parseInt(req.getParameter("ticket_id"));
        boolean checkIn = Boolean.parseBoolean(req.getParameter("checkIn"));
        boolean cancel = Boolean.parseBoolean(req.getParameter("cancel"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
