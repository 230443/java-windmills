package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Location;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;

public interface LocationService
{
    Location getClientLocation(String ip) throws IOException, GeoIp2Exception;
}
