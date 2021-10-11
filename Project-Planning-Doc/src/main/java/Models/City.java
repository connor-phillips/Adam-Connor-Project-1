package Models;

public class City {
    private int city_id;
    private String city;
    private String state;

    public City() {
    }

    public City(int city_id) {
        this.city_id = city_id;
    }

    public City(int city_id, String city) {
        this.city_id = city_id;
        this.city = city;
    }

    public City(int city_id, String city, String state) {
        this.city_id = city_id;
        this.city = city;
        this.state = state;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
