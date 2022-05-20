package be.pxl.windmills.Service;

import be.pxl.windmills.Model.CurrentWeather;
import be.pxl.windmills.Model.WeatherForecast;

import java.util.List;

public interface WeatherService
{
    void saveWeather(CurrentWeather currentWeather);
    List<CurrentWeather> getWeathers();
    CurrentWeather getWeatherByID(Long id);
    CurrentWeather getCurrentWeather(String lat, String lon);
    List<WeatherForecast> getWeatherForecast(String lat, String lon);
}
