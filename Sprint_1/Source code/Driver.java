import java.util.ArrayList;

public class Driver extends User {

    private String nationalString;
    private String drivingLicense;
    private ArrayList<String> favouriteArea = new ArrayList<String>();
    private ArrayList<Ride> rides;
    private boolean isDriving = false;

    // Constructor
    public Driver(String name, String username, String mobile, String email, String password,
                  String nationalString, String drivingLicense) {
        super(name, username, mobile, email, password);
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        isDriving = false;
        rides = new ArrayList<Ride>();
    }

    public boolean isDriving() {
        return isDriving;
    }

    public void setDriving(boolean isDriving) {
        this.isDriving = isDriving;
    }

    // Copy Constructor.
    public Driver(String name, String username, String mobile, String email, String password,
                  String nationalString, String drivingLicense, ArrayList<String> favouriteArea) {
        super(name, username, mobile, email, password);
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        this.favouriteArea = favouriteArea;
    }

    public Driver(String nationalString, String drivingLicense, ArrayList<String> favouriteArea) {
        this.nationalString = nationalString;
        this.drivingLicense = drivingLicense;
        this.favouriteArea = favouriteArea;
    }

    public void addRide(Ride ride)
    {
        rides.add(ride);
    }

    public void listRatings()
    {
        for (int i = 0; i < rides.size(); i++)
        {
            System.out.print("Client's name: " + rides.get(i).getClientName() + " ");
            System.out.println("Rating: " + rides.get(i).getRate());
        }
    }


    @Override
    public String toString() {
        return "Driver [nationalString=" + nationalString + ", drivingLicense=" + drivingLicense + ", Name="
                + getName() + ", Username= " + getUsername() + "]";
    }

    public void addFavArea(String FavArea) {
        favouriteArea.add(FavArea);
    }

    public double getAverageRating()
    {
        double avg = 0;
        for (int i = 0; i < rides.size(); i++)
            avg += rides.get(i).getRate();
        avg /= rides.size();
        return avg;
    }

    public void makeOffer(double price, String username) {
        Offer offer = new Offer(getAverageRating(), price, super.getUsername());
        AppSystem.notifyClient(username, offer);
    }


    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }

    public String getNationalString() {
        return nationalString;
    }

    public void setNationalString(String nationalString) {
        this.nationalString = nationalString;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public ArrayList<String> getFavouriteArea() {
        return favouriteArea;
    }

    public void setFavouriteArea(ArrayList<String> favouriteArea) {
        this.favouriteArea = favouriteArea;
    }


}
