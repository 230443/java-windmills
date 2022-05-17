package be.pxl.windmills.Service;

import javax.servlet.http.HttpServletRequest;

public interface AddressIPService
{
    String getClientIP(HttpServletRequest request);
}
