package servlets;

import Models.Admin;
import Repos.AdminRepo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    Connection conn;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        AdminRepo adminrepo = new AdminRepo(conn);
        try {
            adminrepo.authenticate(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
