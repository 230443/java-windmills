package be.pxl.windmills.Service;

import be.pxl.windmills.Model.*;
import be.pxl.windmills.Repository.WeatherRepository;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService
{
    private final WeatherRepository weatherRepository;
    private final String apiKey = "bec06abd927617a586d4deb2bd5abc27";

    public WeatherServiceImpl(WeatherRepository weatherRepository)
    {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void saveWeather(CurrentWeather currentWeather)
    {
        weatherRepository.save(currentWeather);
    }

    @Override
    public List<CurrentWeather> getWeathers()
    {
        return weatherRepository.findAll();
    }

    @Override
    public CurrentWeather getWeatherByID(Long id)
    {
        return weatherRepository.getById(id);
    }

    @Override
    public CurrentWeather getCurrentWeather(String lat, String lon)
    {
        final String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat +
                           "&lon=" + lon + "&appid=" + this.apiKey +
                           "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OpenWeatherResponse> response = restTemplate.getForEntity(url, OpenWeatherResponse.class);

        return new CurrentWeather(response.getBody().getWeather()[0].getIcon(),
                                  response.getBody().getMain().getTemp(),
                                  response.getBody().getMain().getHumidity(),
                                  response.getBody().getMain().getPressure(),
                                  response.getBody().getWeather()[0].getDescription(),
                                  response.getBody().getSys().getSunrise(),
                                  response.getBody().getSys().getSunset());
    }

    @Override
    public List<WeatherForecast> getWeatherForecast(String lat, String lon)
    {
        List<WeatherForecast> forecast = new ArrayList<>();
        boolean isCurrentDayMorning = false;
        final String url = "https://api.openweathermap.org/data/2.5/forecast?lat=" + lat +
                           "&lon=" + lon + "&appid=" + this.apiKey +
                           "&units=metric";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OpenWeatherResponseForecast> response = restTemplate.getForEntity(url, OpenWeatherResponseForecast.class);

        for(int i = 0; i < response.getBody().getList().size(); i++)
        {
            WeatherList element = response.getBody().getList().get(i);
            DateTime tempDate = new DateTime(Long.parseLong(element.getDt()) * 1000);
            int hour = tempDate.getHourOfDay();

            if(i == 0)
            {
                if(hour < 14)
                {
                    isCurrentDayMorning = true;
                }
                continue;
            }

            if(hour == 23)
            {
                isCurrentDayMorning = false;
                continue;
            }

            if(!isCurrentDayMorning && hour == 14)
            {
                forecast.add(new WeatherForecast(element.getDt(), element.getMain().getTemp(), element.getWeather()[0].getIcon()));
            }
        }

        if(forecast.size() == 5)
        {
            forecast.remove(forecast.size() - 1);
        }

        return forecast;
    }
}
