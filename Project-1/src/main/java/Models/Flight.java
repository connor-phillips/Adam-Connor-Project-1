package Models;

public class Flight {
    private int flight_num;
    private String flight_date;
    private int seat_num;
    private String departure_city;
    private String arrival_city;
    private String departure_time;
    private String arrival_time;

    public Flight(int flight_num, String flight_date, int seat_num, String departure_city, String arrival_city, String departure_time) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
        this.seat_num = seat_num;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
        this.departure_time = departure_time;
    }

    public Flight(int flight_num, String flight_date, int seat_num, String departure_city, String arrival_city) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
        this.seat_num = seat_num;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
    }

    public Flight(int flight_num, String flight_date, int seat_num, String departure_city) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
        this.seat_num = seat_num;
        this.departure_city = departure_city;
    }

    public Flight(int flight_num, String flight_date, int seat_num) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
        this.seat_num = seat_num;
    }

    public Flight(int flight_num, String flight_date) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
    }

    public Flight(int flight_num) {
        this.flight_num = flight_num;
    }

    public Flight(int flight_num, String flight_date, int seat_num, String departure_city, String arrival_city, String departure_time, String arrival_time) {
        this.flight_num = flight_num;
        this.flight_date = flight_date;
        this.seat_num = seat_num;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public Flight() {
    }

    public int getFlight_num() {
        return flight_num;
    }

    public void setFlight_num(int flight_num) {
        this.flight_num = flight_num;
    }

    public String getFlight_date() {
        return flight_date;
    }

    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }

    public int getSeat_num() {
        return seat_num;
    }

    public void setSeat_num(int seat_num) {
        this.seat_num = seat_num;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }
}
