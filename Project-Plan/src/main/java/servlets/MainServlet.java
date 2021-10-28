package servlets;

import utils.FileLogger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServlet extends HttpServlet {

    /**
    This servlet grabs the user input from the Main Menu
    Depending on which button the User presses, they will
    be sent to the corresponding servlet
     */

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            PrintWriter out = resp.getWriter();

            if (req.getParameter("viewFlights") != null) {
                out.println("View Flights");
                RequestDispatcher rd = req.getRequestDispatcher("search");
                rd.forward(req, resp);
            } else if (req.getParameter("cancel") != null) {
                out.println("Cancel Flights");
//            RequestDispatcher rd = req.getRequestDispatcher("login");
//            rd.forward(req, resp);
            } else if (req.getParameter("checkIn") != null) {
                out.println("Check-in for Flights");
//            RequestDispatcher rd = req.getRequestDispatcher("login");
//            rd.forward(req, resp);
            } else if (req.getParameter("changeFlights") != null) {
                out.println("Change Flights");
//            RequestDispatcher rd = req.getRequestDispatcher("login");
//            rd.forward(req, resp);
            } else if (req.getParameter("adminLogin") != null) {
                out.println("Admin Login");
            }
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }


    }
}
