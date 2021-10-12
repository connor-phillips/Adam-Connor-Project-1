package Repos;

import Models.Admin;
import Models.Ticket;
import Models.Flight;
import collections.MyList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepo implements CrudOperations<Admin>{
    Connection conn;

    public AdminRepo(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Admin admin) throws SQLException {

    }

    @Override
    public void save(Flight flight) throws SQLException {
        String sql = "SELECT * FROM flights WHERE flight_num = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flight.getFlight_num());
        ResultSet rs = ps.executeQuery();

        String sqlInsert = "INSERT INTO flights (flight_num, flight_date, seat_num, departure_city, arrival_city, departure_time, arrival_time) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
        psInsert.setInt(1, flight.getFlight_num());
        psInsert.setString(2, flight.getFlight_date());
        psInsert.setInt(3,flight.getSeat_num());
        psInsert.setString(4, flight.getDeparture_city());
        psInsert.setString(5,flight.getArrival_city());
        psInsert.setString(6, flight.getDeparture_time());
        psInsert.setString(7, flight.getArrival_time());

        psInsert.executeUpdate();
    }

    @Override
    public Admin getItemByID(int id) throws SQLException {
        return null;
    }

    @Override
    public MyList<Admin> getAllItems() throws SQLException {
        return null;
    }

    @Override
    public MyList<Admin> getAllItems(Admin admin) throws SQLException {
        return null;
    }

    public Flight showFlight(int flight_num) throws SQLException {
        String sql = "SELECT * FROM flights WHERE flight_num = ?";
        PreparedStatement ps = conn.prepareStatement(sql, 1);
        ps.setInt(1, flight_num);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            //public BankStatement(int transaction_id, int account_id, String account_type, String transaction_type, double amount, double balance)
            Flight flight = new Flight();
            flight.setArrival_time(rs.getString("arrival_time"));
            flight.setDeparture_time(rs.getString("departure_time"));
            flight.setArrival_city(rs.getString("arrival_city"));
            flight.setDeparture_city(rs.getString("departure_city"));
            flight.setFlight_date(rs.getString("flight_date"));
            flight.setSeat_num(rs.getInt("seat_num"));
            return flight;
        } else {
            return null;
        }
    }

    public Admin authenticate(Admin admin) throws SQLException {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, admin.getUsername());
        ps.setString(2, admin.getPassword());
        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            // public Admin(int admin_id, String username, String password, String first_name, String last_name)
            return new Admin(rs.getInt("admin_id"), rs.getString("username"), rs.getString("password"), rs.getString("first_name"), rs.getString("last_name"));
        } else {
            return null;
        }
    }

    public MyList<Flight> showAllFlights() throws SQLException {
        String sql = "SELECT * FROM flights";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        MyList<Flight> flights = new MyList<Flight>();

        if (rs.next()){
            Flight row = new Flight();
            row.setFlight_num(rs.getInt("flight_num"));
            row.setFlight_date(rs.getString("flight_date"));
            row.setDeparture_time(rs.getString("departure_time"));
            row.setArrival_time(rs.getString("arrival_time"));
            row.setDeparture_city(rs.getString("departure_city"));
            row.setArrival_city(rs.getString("arrival_city"));

            flights.add(row);
        }
        return flights;
    }

    public MyList<Ticket> showAllPassengers(Flight flight) throws SQLException {
        String sql = "SELECT * tickets FROM flight_num = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flight.getFlight_num());
        ResultSet rs = ps.executeQuery();

        MyList<Ticket> customers = new MyList<Ticket>();

        while (rs.next()) {
            Ticket row = new Ticket();
            row.setFirst_name(rs.getString("first_name"));
            row.setLast_name(rs.getString("last_name"));
            row.setSeat_num(rs.getInt("seat_num"));
            row.setTicket_id(rs.getInt("ticket_id"));

            customers.add(row);
        }
        return customers;
    }

    @Override
    public void deleteByID(int flight_num) throws SQLException {
        String sql = "DELETE FROM flights WHERE flight_num = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, flight_num);
        ResultSet rs = ps.executeQuery();
    }
}
