package be.pxl.windmills.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.pxl.windmills.Model.CurrentWeather;
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
    public void saveWeather(@RequestBody CurrentWeather currentWeather)
    {
        weatherService.saveWeather(currentWeather);
    }

    @GetMapping("weather-list")
    public List<CurrentWeather> allWeathers()
    {
        return weatherService.getWeathers();
    }

    @GetMapping("weather/{weather_id}")
    public CurrentWeather weatherByID(@PathVariable("weather_id") Long weather_id)
    {
        return weatherService.getWeatherByID(weather_id);
    }

    @PostMapping("current-weather")
    public CurrentWeather getCurrentWeather(@RequestParam(value="lat" )String lat, @RequestParam(value="lon" )String lon)
    {
        return weatherService.getCurrentWeather(lat, lon);
    }
}
