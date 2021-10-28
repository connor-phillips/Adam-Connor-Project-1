package servlets.Admin;

import Models.Admin;
import Models.Flight;
import services.AdminService;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * This is the Admin Login Servlet. This servlet will request a username and password and authenticate them, ensuring
 * that they exist in our Admin table. If they are authenticated they would be logged in, if not they would be prompted
 * with an error.
 */

public class LoginServlet extends HttpServlet {
    Admin admin;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            super.doGet(req, resp);
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
        /**
         * Post method to log in a registered admin
         */
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            admin.setUsername(username);
            admin.setPassword(password);
            PrintWriter out = resp.getWriter();
            if (AdminService.authenticate(admin) == null) {
                out.println("This Admin does not exist");
            } else {
                admin = AdminService.authenticate(admin);
                out.println("Welcome: " + admin.getFirst_name());
            }
        } catch(IOException e){
            FileLogger.getFileLogger().console().threshold(4).writeLog(e.toString(), 4);
        }

    }

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //Register a new Admin
//        Admin newAdmin = new Admin();
//        newAdmin.setFirst_name(req.getParameter("firstName"));
//        newAdmin.setLast_name(req.getParameter("lastName"));
//        newAdmin.setUsername(req.getParameter("username"));
//        newAdmin.setPassword(req.getParameter("password"));
//        AdminService.registerAdmin(newAdmin);
//    }
}
