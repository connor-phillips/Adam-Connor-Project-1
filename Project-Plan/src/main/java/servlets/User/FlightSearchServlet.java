package servlets.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FlightSearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departure = req.getParameter("departureCity");
        String arrival = req.getParameter("arrivalCity");
        String date = req.getParameter("departureDate");

        PrintWriter out = resp.getWriter();
        out.println("Departure Date: " + departure + "\n"
        + "Arrival City: " + arrival + "\n" +
                "Flight Date: " + date + "\n");
    }
}
