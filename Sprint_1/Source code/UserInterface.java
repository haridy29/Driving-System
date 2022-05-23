import java.util.*;

public class UserInterface {

    static Database database;

    public UserInterface()
    {
        database = Database.getDatabase();
        mainMenu();
    }

    /**
     * get user's input
     */
    Scanner scan = new Scanner(System.in);

    public void signUpClient(Client client) {
        database.addClient(client);
    }

    public void signUpDriver(Driver driver) {
        database.addPendingDriver(driver);
    }

    public void signUpAdmin(Admin admin) {
        database.addAdmin(admin);
    }

    public void loginAsAdmin(String username, String password) {
        boolean valid = AppSystem.isValidAdmin(username, password);
        if (!valid) {
            System.out.println("Wrong username or password");
            mainMenu();
        } else {
            System.out.println("\t\tWelcome " + username + "\n");
            Admin admin = database.getAdmin(username);
            adminMenu(admin);
        }
    }

    /**
     * Show Administrator screen
     * */
    public void adminMenu(Admin admin)
    {
        int choice = 0;
        boolean valid = true;

        boolean correctChoice = false;
        while(valid)
        {

            while(!correctChoice)
            {
                System.out.println("1- Show all pending drivers.\n"
                        +"2- Suspend driver.\n"
                        +"3- Suspend user account.\n"
                        +"4- logOut");

                choice = scan.nextInt();

                if(choice >=1 && choice <= 4)
                {
                    correctChoice = true;
                }
                else
                {
                    correctChoice = false;
                }
            }

            switch(choice)
            {
                case 1:
                {
                    /*
                     The admin user should be able to verify driver registration. So the admin should be able to
                     list all pending driver registrations and verify any pending driver registration.
                     */

                    int size = database.getPendingDrivers().size();
                    admin.listPendingDrivers();
                    if (size > 0) {
                        System.out.println("Enter driver's name: ");
                        String name = scan.next();
                        if (admin.verifyDriver(name)) {
                            System.out.println("Verified successfully!");
                        }
                        else {
                            System.out.println("Driver not found.");
                        }
                    }

                    correctChoice = false;
                    break;
                }

                case 2:
                {
                    System.out.println("Enter driver name.");

                    String name = scan.next();
                    boolean done = admin.suspendDriver(name);
                    if(done){
                        System.out.println("diver has been deleted successfully.");
                    }
                    else {
                        System.out.println("driver name isn't correct.");
                        System.out.println("pls try again.");
                    }
                    correctChoice = false;
                    break;

                }

                case 3:
                {
                    System.out.println("Enter user name.");

                    String name = scan.next();
                    boolean done = admin.suspendClient(name);
                    if(done){
                        System.out.println("User has been deleted successfully.");
                    }
                    else {
                        System.out.println("User name isn't correct.");
                        System.out.println("please try again.");
                    }
                    correctChoice = false;
                    break;
                }
                case 4:
                {
                    valid = false;
                    mainMenu();
                    break;

                }

                default:
                {
                    valid = true;
                    correctChoice = false;
                }
            }

        }
    }

    public void mainMenu() {
        System.out.println("Main Menu");
        System.out.println("1- Login as admin");
        System.out.println("2- Login as a client");
        System.out.println("3- Login as a driver");
        System.out.println("4- Signup as a client");
        System.out.println("5- Signup as a driver");
        int choice = scan.nextInt();
        if (choice == 2) {
            System.out.println("Enter username: ");
            String username = scan.next();
            System.out.println("Enter password: ");
            String password = scan.next();
            loginAsClient(username, password);
        } else if (choice == 3) {
            System.out.println("Enter username: ");
            String username = scan.next();
            System.out.println("Enter password: ");
            String password = scan.next();
            loginAsDriver(username, password);
        } else if (choice == 5 || choice == 4) {
            System.out.println("Email: ");
            String email = scan.next();
            System.out.println("Password: ");
            String password = scan.next();
            System.out.println("username: ");
            String username = scan.next();
            System.out.println("Phone number: ");
            String phone = scan.next();
            System.out.println("Real name: ");
            String realName = scan.next();
            if (choice == 4) {
                Client client = new Client(realName, username, phone, email, password);
                boolean valid = !AppSystem.isValidClient(username, password);
                if (valid) {
                    System.out.println("Your account is registered successfully");
                    signUpClient(client);
                } else {
                    System.out.println("Your account is already registered. You can login");
                }
                mainMenu();
            } else {
                System.out.print("National ID: ");
                String nationalID = scan.next();
                System.out.print("Driver License: ");
                String driverLicense = scan.next();
                Driver driver = new Driver(realName, username, phone, email, password, nationalID, driverLicense);
                boolean valid = !AppSystem.isValidDriver(username, password);
                if (valid) {
                    System.out.println("Your account is registered successfully");
                    signUpDriver(driver);
                } else {
                    System.out.println("Your account is already registered. You can login");
                }
                mainMenu();
            }
        }
        else if (choice == 1)
        {
            System.out.println("Username: ");
            String username = scan.next();
            System.out.println("Password: ");
            String password = scan.next();
            loginAsAdmin(username, password);
        }
        else {
            System.out.println("Incorrect Input");
            mainMenu();
        }
    }

    public void loginAsClient(String username, String password) {
        boolean valid = AppSystem.isValidClient(username, password);
        if (!valid) {
            System.out.println("Wrong username or password");
            mainMenu();
        } else {
            System.out.println("\t\tWelcome " + username + "\n");
            Client client = database.getClient(username);
            clientMenu(client);
        }
    }

    public void loginAsDriver(String username, String password) {
        boolean valid = AppSystem.isValidDriver(username, password);
        if (!valid) {
            System.out.println("Wrong username or password");
            mainMenu();
        } else {
            System.out.println("\t\tWelcome " + username + "\n");
            Driver driver = database.getDriver(username);
            driverMenu(driver);
        }
    }

    /**
     * Show driver screen
     */
    public void driverMenu(Driver driver) {
        if (driver.isDriving())
        {
            System.out.println("You are now on a ride!");
            driver.getNotifications().clear();
        }
        while (true) {
            System.out.println("Driver Menu");
            System.out.println("1- View notifications");
            System.out.println("2- Add favourite area");
            System.out.println("3- Show users' rate");
            System.out.println("4- Logout");
            int choice = scan.nextInt();
            if (choice == 1) {
                int notifications = driver.getNotifications().size();
                if (notifications == 0) {
                    System.out.println("You have no notifications");
                } else {
                    for (int i = 0; i < notifications; i++) {
                        System.out.print(i + 1 + ")" + driver.getNotifications().get(i));
                    }
                    System.out.print("Enter the number of notification if you want to make an offer ");
                    System.out.println("or enter 0 to go back");
                    int idx = scan.nextInt();
                    if (idx == 0) {
                        continue;
                    } else if (idx > notifications) {
                        System.out.println("Wrong number!");
                        continue;
                    } else {
                        System.out.println("Enter the price you want to offer for this ride.");
                        double price = scan.nextDouble();
                        idx--;
                        Offer offer = new Offer(driver.getAverageRating(), price, driver.getUsername());
                        String notification = driver.getNotifications().get(idx);
                        String[] words = notification.split(" ");
                        String username = words[words.length - 1];
                        System.out.println(username + "bta3 ui");
                        AppSystem.notifyClient(username, offer);
                        System.out.println("Your offer was sent to the client");

                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter a new favourite area");
                String area = scan.next();
                driver.addFavArea(area);
                System.out.println("A new area was added successfully.");
                System.out.println("You will be notified we there is a pending ride at this area");
            } else if (choice == 3) {
                ArrayList<Ride> rides = driver.getRides();
                if (rides.size() == 0) {
                    System.out.println("No past rides");
                }
                else {
                    for (int i = 0; i < rides.size(); i++) {
                        System.out.println(rides.get(i).toString());
                    }
                }
            } else if (choice == 4) {
                break;
            }
        }
        mainMenu();
    }

    /**
     * Show client screen
     */

    public void clientMenu(Client var1) {
        Scanner input = new Scanner(System.in);

        if (var1.getOffers() != null && var1.getOffers().size() > 0) {
            System.out.println("notification:-\n" +
                    "You Have new offers please check it");
        }

        while (true) {
            System.out.println("Please Enter the number of your choice...");

            System.out.println("""
                    1- Ride Request
                    2- Check offers
                    3- Rate Driver
                    4- Logout""");
            short choice = input.nextShort();
            if (choice == 1) {
                if (var1.getRide()!=null&&var1.getRide().getOffer() != null) {
                    System.out.println("You Already in Ride!...");

                } else {
                    String Source, Dest;
                    System.out.println("Source: ");
                    Source = input.next();
                    System.out.println("Destination: ");
                    Dest = input.next();
                    var1.requestRide(Source, Dest);
                    System.out.println("Waiting Driver...");
                }
            } else if (choice == 2) {
                if (var1.getOffers()!=null) {
                    var1.listOffers();
                    System.out.println("Choose offer...");
                    System.out.print("Offer: ");
                    int choose_offer = input.nextInt();
                    boolean offerexist = var1.chooseOffer(choose_offer);

                    while (!offerexist) {
                        choose_offer = input.nextInt();
                        offerexist = var1.chooseOffer(choose_offer);
                    }
                    System.out.println("Ride start...");
                }else {
                    System.out.println("You Have No offers...");
                }
            } else if (choice == 3) {
                if (var1.getRide().getOffer() == null) {
                    System.out.println("Sorry The Ride not assigned...");
                } else {
                    System.out.println("Please Enter the Rate From 1 to 5 ...");
                    System.out.print("Enter Rate: ...");
                    double rate = input.nextDouble();
                    while (rate < 0.0 || rate > 5.0) {
                        System.out.println("Rate must be From 1 to 5, Please Try again...");
                        System.out.print("Enter Rate: ...");
                        rate = input.nextDouble();
                    }
                    var1.rateDriver(rate);
                    System.out.println("You are Reach...");
                }

            } else if (choice == 4) {
                System.out.println("Logged out");
                mainMenu();
            } else {
                System.out.println("Invalid input please Try again...");
            }


        }


        //throw new Error("Unresolved compilation problem: \n");
    }

}
