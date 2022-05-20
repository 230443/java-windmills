package be.pxl.windmills.Service;

import be.pxl.windmills.Model.OpenWeatherResponse;
import be.pxl.windmills.Model.CurrentWeather;
import be.pxl.windmills.Model.Weather;
import be.pxl.windmills.Repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService
{
    private final WeatherRepository weatherRepository;

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
        final String apiKey = "bec06abd927617a586d4deb2bd5abc27";
        final String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat +
                           "&lon=" + lon + "&appid=" + apiKey +
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
}
