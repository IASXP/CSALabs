public class Place {
    String name;
    String address;
    GeoLocation location;

    public Place(String n, String a, double lati, double longi)
    {
        name = n;
        address = a;
        location = new GeoLocation(lati, longi);
    }

    public Place(String n, String a, GeoLocation loc)
    {
        name = n;
        address = a;
        location = loc;
    }
    public double distanceTo(Place other)
    {
        return location.distanceTo(other.location);
    }

    public String toString()
    {
        return name + "\n" + address + "\n" + "latitude: " + location.latitude + ", longitude: " + location.longitude;
    }

}
