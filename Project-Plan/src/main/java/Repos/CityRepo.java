package Repos;

import Models.City;
import Models.Flight;
import collections.MyList;

import java.sql.SQLException;

public class CityRepo implements CrudOperations<City>{
    @Override
    public void save(City city) throws SQLException{

    }

    @Override
    public void save(Flight flight) throws SQLException {

    }

    @Override
    public City getItemByID(int id) throws SQLException {
        return null;
    }

    @Override
    public MyList<City> getAllItems() throws SQLException {
        return null;
    }

    @Override
    public MyList<City> getAllItems(City city) throws SQLException {
        return null;
    }

    @Override
    public void deleteByID(int id) throws SQLException {

    }
}
