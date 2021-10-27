package Models;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="flights")
public class Flight {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer flight_num;

    @Column
    private String origin;

    @Column
    private String destination;

    @Column
    private String date;

    @Column
    private String time;

    @Column
    private Boolean cancelled;

    @OneToMany(cascade=CascadeType.ALL)
    public List<Ticket> tickets = new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL)
    public User user;

    public Flight() {
    }

    public Flight(String origin, String destination, String date, String time, Boolean cancelled) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.cancelled = cancelled;
    }

    public Integer getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(Integer flight_num) {
        this.flight_num = flight_num;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
