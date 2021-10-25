package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer user_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @OneToOne
    @JoinColumn(name = "flight_num", referencedColumnName = "flight_num")
    public Flight flight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Ticket> tickets = new LinkedList<>();

    public User() {
    }

    public User(String first_name, String last_name, Flight flight) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.flight = flight;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
