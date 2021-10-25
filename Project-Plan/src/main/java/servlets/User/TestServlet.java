package servlets.User;

import Models.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.CityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<City> cities = CityService.getAllCities();
        ObjectMapper mapper = new ObjectMapper();
        resp.getWriter().write(mapper.writeValueAsString(cities));
        resp.setContentType("application/json");
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
