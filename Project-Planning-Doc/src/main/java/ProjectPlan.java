public class ProjectPlan {
    public static void main(String[] args) {
        /*
        *User Interface*

        * Login Screen
        * Users vs. Admin vs. Pilot
        *
        * Main Menu: (Users)
        1. View FLights
        2. Book FLights
        3. Change Flights
        4. Cancel Flights
        5. Check-in for Flights

        Main Menu (Admin):
        1. Schedule Flight
        2. Cancel Flight
        3. View all Flight Passengers
        4. Cancel Ticket *Bonus*

        Main Menu (Pilot): *Bonus*
        1. Initiate Takeoff
        *
        *
        *
        * Data Base Tables (MariaDB)
        *
        * Customers Table
        *   Primary Key - Customer E-mail
        *   Customer ID
        *   Ticket # - CustomerID + CityID + FlightID
        *
        * Manifest Table
        *   Primary Key - Manifest ID
        *   Customer ID
        *   City ID
        *   Flight ID
        *   Ticket # - CustomerID + CityID + FlightID
        *   Check-in Status
        *
        * Flights Table(s)
        *   Primary Key - Flight ID/#
        *   Departure City
        *   Arrival City
        *   Departure Time
        *   Arrival Time
        *   Ticket Prices
        *   Seat #
        *
        * Junction Table
        *       Foreign Key - Flight ID/#
        *       Foreign Key - City ID/#
        *
        * City Table
        *   Primary Key - City ID/# (Auto-Increment)
        *
        * Pricing Table
        *   Price Tiers
        *       1, 2, and 3 (Based on Departure)
        *
        *
        *
        *
        *
        * First Steps:
        * 1. Menus - Users, Admin, Pilot (View Manager?)
        * 2. Database - Flight Info, Customer Info, City Table, Junction Table, Pricing Table(Tiers), Manifest Table
        * 3. Servlets - Dispatcher and Helper Servlets
         * */
    }
}
