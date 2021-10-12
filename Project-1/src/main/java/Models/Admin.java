package Models;

public class Admin {
    private int admin_id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    public Admin() {
    }

    public Admin(int admin_id) {
        this.admin_id = admin_id;
    }

    public Admin(int admin_id, String username) {
        this.admin_id = admin_id;
        this.username = username;
    }

    public Admin(int admin_id, String username, String password) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
    }

    public Admin(int admin_id, String username, String password, String first_name) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
    }

    public Admin(int admin_id, String username, String password, String first_name, String last_name) {
        this.admin_id = admin_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
