package servlets.Admin;

import Models.Admin;
import services.AdminService;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the Register Servlet. This servlet will retrieve the admin's First Name, Last Name, Username, and Password
 * from the http request. It will create a new admin using these parameters and store them in the admin table.
 */

public class RegisterServlet extends HttpServlet {

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
        //Grab the parameters from the client request
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            //Instantiate a new Admin class object
            Admin admin = new Admin();

            //Assign the parameters from the client request to the Admin class fields
            admin.setFirst_name(firstName);
            admin.setLast_name(lastName);
            admin.setUsername(username);
            admin.setPassword(password);

            //Grab the String returned by the registerAdmin method within the AdminService class
            String response = AdminService.registerAdmin(admin);

            //Pass the returned String to the PrintWriter object
            PrintWriter out = resp.getWriter();
            out.println(response);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }
}
