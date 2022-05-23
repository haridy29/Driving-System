import java.util.*;

public class AppSystem {
    static Database database = Database.getDatabase();

    public static boolean isValidAdmin(String username, String password)
    {
        ArrayList<Admin> admins = database.getAdmins();

        for (int i = 0; i < admins.size(); i++)
        {
            String curUsername = admins.get(i).getUsername();
            String curPassword = admins.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isValidClient(String username, String password)
    {
        ArrayList<Client> clients = database.getClients();
        for (int i = 0; i < clients.size(); i++)
        {
            String curUsername = clients.get(i).getUsername();
            String curPassword = clients.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidDriver(String username, String password)
    {
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            String curPassword = drivers.get(i).getPassword();
            if (curUsername.equals(username) && curPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean setDriverStatus(String username, boolean flag)
    {
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            if (curUsername.equals(username)) {
                drivers.get(i).setDriving(flag);
                return true;
            }
        }
        return false;
    }



    public static void notifyDrivers(String username, String area)
    {
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            for (int j = 0; j < drivers.get(i).getFavouriteArea().size(); j++)
            {
                String curArea = drivers.get(i).getFavouriteArea().get(j);
                if (curArea.equals(area))
                {
                    String notification = "You have a pending ride at " + area + ".\n";
                    notification += "Client's name is " + username;
                    drivers.get(i).addNotification(notification);
                }
            }
        }
    }

    public static void notifyClient(String username, Offer offer)
    {
        ArrayList<Client> clients = database.getClients();
        for (int i = 0; i < clients.size(); i++)
        {
            if (clients.get(i).getUsername().equals(username))
            {
                clients.get(i).addOffer(offer);
                return;
            }
        }
    }


    public static void addRide(Ride ride)
    {
        String username = ride.getOffer().getDriverName();
        ArrayList<Driver> drivers = database.getDrivers();
        for (int i = 0; i < drivers.size(); i++)
        {
            String curUsername = drivers.get(i).getUsername();
            if (curUsername.equals(username)) {
                drivers.get(i).addRide(ride);
                drivers.get(i).getNotifications().clear();
            }
        }
    }

}
