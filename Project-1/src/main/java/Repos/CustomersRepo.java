package Repos;

import Models.Customer;
import Models.Flight;
import collections.MyList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersRepo implements CrudOperations<Customer>{
    Connection conn;

    public CustomersRepo(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        String sql = "SELECT * CUSTOMERS WHERE customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, customer.getCustomer_id());
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
    public Customer getItemByID(int customer_id) throws SQLException {
        String sql = "SELECT * CUSTOMERS WHERE customer_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, customer_id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            return new Customer(rs.getInt("customer_id"), rs.getString("first_name"), rs.getString("last_name"));
        } else {
            return null;
        }
    }

    @Override
    public MyList<Customer> getAllItems() throws SQLException {
        return null;
    }

    @Override
    public MyList<Customer> getAllItems(Customer customer) throws SQLException {
        return null;
    }

    @Override
    public void deleteByID(int id) throws SQLException {

    }
}
