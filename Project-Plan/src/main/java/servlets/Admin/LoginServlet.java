package servlets.Admin;

import Models.Admin;
import Models.Flight;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //Login a registered Admin
        System.out.println("Servlet is Reached");

        String username = req.getParameter("Username");
        String password = req.getParameter("Password");

        PrintWriter out = resp.getWriter();
        out.println("Welcome " + username);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //Register a new Admin
        Admin newAdmin = new Admin();
        newAdmin.setFirst_name(req.getParameter("firstName"));
        newAdmin.setLast_name(req.getParameter("lastName"));
        newAdmin.setUsername(req.getParameter("username"));
        newAdmin.setPassword(req.getParameter("password"));
        AdminService.registerAdmin(newAdmin);
    }
}
