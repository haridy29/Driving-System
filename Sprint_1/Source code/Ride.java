public class Ride {
    private String source;
    private String destination;
    private String clientName;
    private double rate;
    private Offer offer;

    public Ride(String source,String destination){
        this.source=source;
        this.destination=destination;
    }



    public double getRate() {
        return rate;
    }



    public void setRate(double rate) {
        this.rate = rate;
    }


    @Override
    public String toString() {
        String ret = "Source: " + source + "\n" + "Destination: " + destination + "\n";
        ret += "Client's name: " + clientName + "\n" + "Rate: " + rate;
        return ret;
    }



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String userName) {
        clientName = userName;
    }

}