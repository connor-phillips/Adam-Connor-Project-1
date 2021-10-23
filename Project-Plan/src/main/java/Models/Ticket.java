package Models;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @Column
    @GeneratedValue
    private Integer ticket_id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Integer customer_id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Integer flight_num;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private Boolean checkIn;

    @Column
    private Boolean cancel;

    public Ticket() {
    }

    public Ticket(Integer ticket_id, Integer customer_id, Integer flight_num, String first_name, String last_name, Boolean checkIn, Boolean cancel) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.flight_num = flight_num;
        this.first_name = first_name;
        this.last_name = last_name;
        this.checkIn = checkIn;
        this.cancel = cancel;
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

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(int flight_num) {
        this.flight_num = flight_num;
    }

    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }
}
