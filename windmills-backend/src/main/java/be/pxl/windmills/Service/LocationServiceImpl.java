package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Location;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class LocationServiceImpl implements LocationService
{
    private DatabaseReader dbReader;

    public LocationServiceImpl() throws IOException
    {
        File database = new File("src/main/locationDB/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    @Override
    public Location getClientLocation(String ip) throws IOException, GeoIp2Exception
    {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String cityName = response.getCity().getName();
        String latitude =
                response.getLocation().getLatitude().toString();
        String longitude =
                response.getLocation().getLongitude().toString();
        return new Location(ip, cityName, latitude, longitude);
    }
}
