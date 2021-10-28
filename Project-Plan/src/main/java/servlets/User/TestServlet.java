package servlets.User;

import Models.City;
import services.CityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        City newCity = new City();
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        newCity.setCity(city);
        newCity.setState(state);
        CityService.saveNewCity(newCity);
    }
}
