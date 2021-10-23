package servlets.Admin;

import Models.Admin;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Grab the parameters from the client request
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
    }
}
