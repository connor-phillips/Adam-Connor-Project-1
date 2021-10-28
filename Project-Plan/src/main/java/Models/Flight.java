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
    private Integer flight_num;

    @Column
    private String origin;

    @Column
    private String destination;

    @Column
    private String date;

    @Column
    private String time;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Ticket> tickets = new LinkedList<>();

    public Flight() {
    }

    public Flight(String origin, String destination, String date, String time) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
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
}
