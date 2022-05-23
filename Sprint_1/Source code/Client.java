import java.util.ArrayList;

public class Client extends User{

    Ride ride;

    ArrayList<Offer> offers;

    public Client(String name, String username, String mobile, String email, String password) {
        super(name, username, mobile, email, password);
        offers = new ArrayList<Offer>();
    }

    public void requestRide(String source, String destination){
        ride = new Ride(source, destination);
        ride.setClientName(super.getUsername());
        AppSystem.notifyDrivers(this.getUsername(), source);
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public void addOffer(Offer offer)
    {
        offers.add(offer);
    }

    public void rateDriver(double rate)
    {
        ride.setRate(rate);
        AppSystem.addRide(ride);
        AppSystem.setDriverStatus(ride.getOffer().driverName, false);
    }

    public void listOffers()
    {
        for (int i = 0; i < offers.size(); i++)
        {
            System.out.print(i + 1 + ") ");
            offers.get(i).printOffer();
        }
    }

    public boolean chooseOffer(int idx)
    {
        idx--;
        if (idx<0||idx>offers.size()-1){
            System.out.println("Offer Not Found\n");
            return false;
        }
        ride.setOffer(offers.get(idx));
        String username = offers.get(idx).getDriverName();
        AppSystem.setDriverStatus(username, true);
        offers.clear();
        return true;
    }

}