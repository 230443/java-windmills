package be.pxl.windmills.Model;

public class Location
{
    private String ipAddress;
    private String city;
    private String latitude;
    private String longitude;

    public Location(String ip, String city, String lat, String lon)
    {
        this.ipAddress = ip;
        this.city = city;
        this.latitude = lat;
        this.longitude = lon;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
}
