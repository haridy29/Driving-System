import java.util.*;

public class Admin {

    private String username;
    private String password;
    static Database database = Database.getDatabase();

    public Admin()
    {
        this.username = "admin";
        this.password = "admin";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void listPendingDrivers()
    {
        database = Database.getDatabase();
        Queue<Driver> pendingDriver = database.getPendingDrivers();
        if (pendingDriver.size() == 0) {
            System.out.println("No pending drivers");
            return;
        }
        int size = database.getPendingDrivers().size();
        for (int i = 0; i < size; i++)
        {
            Driver driver = pendingDriver.peek();
            pendingDriver.poll();
            pendingDriver.add(driver);
            System.out.print(i + 1 + ") ");
            System.out.println(driver.toString());
        }
    }

    public boolean verifyDriver(String driverName)
    {
        int Size = database.getPendingDrivers().size();
        System.out.println(Size + " hena");
        for (int i = 0; i < Size; i++)
        {
            Driver driver = database.getPendingDrivers().peek();
            database.popPendingDriver();
            if (driver.getUsername().equals(driverName) == false)
            {
                database.addPendingDriver(driver);
            }
            database.addDriver(driver);
            return true;
        }
        return false;
    }

    public boolean suspendClient(String username)
    {
        for (int i = 0; i < database.getClients().size(); i++)
        {
            if (username.equals(database.getClients().get(i).getUsername())) {
                database.getClients().get(i).setActive(false);
                return true;
            }
        }
        return false;
    }

    public boolean suspendDriver(String username)
    {
        for (int i = 0; i < database.getDrivers().size(); i++)
        {
            if (username.equals(database.getDrivers().get(i).getUsername())) {
                database.getDrivers().get(i).setActive(false);
                return true;
            }
        }
        return false;
    }
}