package servlets.Admin;

import services.FlightService;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CancelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            super.doGet(req, resp);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        try {
            Integer flight_num = Integer.parseInt(req.getParameter("flight_id"));

            String alert;

            //alert = FlightService.cancelFlight(flight_num);

            PrintWriter out = resp.getWriter();
            //out.println(alert);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }
}
