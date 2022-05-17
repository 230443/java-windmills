package be.pxl.windmills.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.pxl.windmills.Model.Weather;
import be.pxl.windmills.Service.WeatherService;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/weatherAPI")
public class WeatherController
{
    @Autowired
    private WeatherService weatherService;

    @PostMapping("save-weather")
    public void saveWeather(@RequestBody Weather weather)
    {
        weatherService.saveWeather(weather);
    }

    @GetMapping("weather-list")
    public List<Weather> allWeathers()
    {
        return weatherService.getWeathers();
    }

    @GetMapping("weather/{weather_id}")
    public Weather weatherByID(@PathVariable("weather_id") Long weather_id)
    {
        return weatherService.getWeatherByID(weather_id);
    }

    @GetMapping("current-weather")
    public Weather getCurrentWeather()
    {
        return weatherService.getCurrentWeather();
    }
}
