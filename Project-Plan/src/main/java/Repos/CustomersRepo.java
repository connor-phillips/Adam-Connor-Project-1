package Repos;

import Models.User;
import Models.Flight;
import collections.MyList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersRepo implements CrudOperations<User>{
    Connection conn;

    public CustomersRepo(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(User customer) throws SQLException {
        String sql = "SELECT * CUSTOMERS WHERE customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, customer.getUser_id());
        ResultSet rs = ps.executeQuery();

        String sqlInsert = "INSERT INTO users (first_name, last_name) VALUES (?, ?)";
        PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
        psInsert.setString(1, customer.getFirst_name());
        psInsert.setString(2, customer.getLast_name());

        psInsert.executeUpdate();
    }

    @Override
    public void save(Flight flight) throws SQLException {

    }

    @Override
    public User getItemByID(int customer_id) throws SQLException {
        String sql = "SELECT * CUSTOMERS WHERE customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, customer_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            return new User(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"));
        } else {
            return null;
        }
    }

    @Override
    public MyList<User> getAllItems() throws SQLException {
        return null;
    }

    @Override
    public MyList<User> getAllItems(User customer) throws SQLException {
        return null;
    }

    @Override
    public void deleteByID(int id) throws SQLException {

    }
}
