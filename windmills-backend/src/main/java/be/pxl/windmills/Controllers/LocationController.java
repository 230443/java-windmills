package be.pxl.windmills.Controllers;

import be.pxl.windmills.Model.Location;
import be.pxl.windmills.Service.AddressIPService;
import be.pxl.windmills.Service.LocationService;
import be.pxl.windmills.Service.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/locationAPI")
public class LocationController
{
    @Autowired
    private AddressIPService addressIPService;

    @Autowired
    private LocationService locationService;

    public LocationController() throws IOException
    {
        locationService = new LocationServiceImpl();
    }

    @GetMapping("get-location")
    public Location getClientLocation(HttpServletRequest request) throws Exception
    {
        //String clientIP = addressIPService.getClientIP(request);

        String clientIP = "2a02:1810:9540:ec00:38c5:22a9:8313:c3a";
        return locationService.getClientLocation(clientIP);
    }
}
