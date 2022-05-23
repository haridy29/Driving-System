import java.util.*;

public class Database {
    ArrayList<Client> clients;
    ArrayList<Driver> drivers;
    Queue <Driver> pendingDrivers;
    ArrayList<Admin> admins;
    private static Database database;

    private Database()
    {
        clients = new ArrayList<Client> ();
        drivers = new ArrayList<Driver> ();
        pendingDrivers = new LinkedList<Driver> ();
        admins = new ArrayList<>();
        // To create the default admin once we create a database
        admins.add(new Admin());
    }

    public void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public Admin getAdmin(String username) {

        for (int i = 0; i < admins.size(); i++) {

            if (admins.get(i).getUsername().equals(username)) {

                return admins.get(i);
            }
        }
        return null;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }


    public Client getClient(String username)
    {
        for (int i = 0; i < clients.size(); i++)
        {
            if (clients.get(i).getUsername().equals(username)) {
                return clients.get(i);
            }
        }
        return null;
    }

    public Driver getDriver(String username)
    {
        for (int i = 0; i < drivers.size(); i++)
        {
            if (drivers.get(i).getUsername().equals(username)) {
                return drivers.get(i);
            }
        }
        return null;
    }

    public static Database getDatabase()
    {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void addClient(Client client)
    {
        clients.add(client);
    }

    public void addDriver(Driver driver)
    {
        drivers.add(driver);
    }

    public void addPendingDriver(Driver driver)
    {
        System.out.println(pendingDrivers.add(driver));
        System.out.println(pendingDrivers.size());
    }

    public void popPendingDriver()
    {
        pendingDrivers.poll();
    }


    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(ArrayList<Driver> drivers) {
        this.drivers = drivers;
    }

    public Queue<Driver> getPendingDrivers() {
        return pendingDrivers;
    }

    public void setPendingDrivers(Queue <Driver> pendingDrivers) {
        this.pendingDrivers = pendingDrivers;
    }

}
