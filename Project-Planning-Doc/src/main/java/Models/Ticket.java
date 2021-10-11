package Models;

public class Ticket {
    private int ticket_id;
    private int customer_id;
    private String first_name;
    private String last_name;

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

    private int flight_num;
    private int seat_num;
    private double price;

    public Ticket() {
    }

    public Ticket(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Ticket(int ticket_id, int customer_id) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
    }

    public Ticket(int ticket_id, int customer_id, int flight_num) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.flight_num = flight_num;
    }

    public Ticket(int ticket_id, int customer_id, int flight_num, int seat_num) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.flight_num = flight_num;
        this.seat_num = seat_num;
    }

    public Ticket(int ticket_id, int customer_id, int flight_num, int seat_num, double price) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.flight_num = flight_num;
        this.seat_num = seat_num;
        this.price = price;
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

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
