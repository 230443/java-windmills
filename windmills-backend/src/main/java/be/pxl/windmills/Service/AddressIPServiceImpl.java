package be.pxl.windmills.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AddressIPServiceImpl implements AddressIPService
{
    private static final String[] HEADERS= {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"};

    @Override
    public String getClientIP(HttpServletRequest request)
    {
        String ipAddress = null;

        for (String header : HEADERS)
        {
            ipAddress = request.getHeader(header);
            if (ipAddress != null && ipAddress.length() > 0)
            {
                return StringUtils.commaDelimitedListToStringArray(ipAddress)[0];
            }
        }

        if(!StringUtils.hasLength(ipAddress))
        {
            ipAddress = request.getRemoteAddr();
            String LOCALHOST_IPV4 = "127.0.0.1";
            String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
            if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress))
            {
                try
                {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                }
                catch(UnknownHostException e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(StringUtils.hasLength(ipAddress)
           && ipAddress.length() > 15
           && ipAddress.indexOf(",") > 0)
        {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }
}
