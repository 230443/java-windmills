package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Weather;

import java.util.List;

public interface WeatherService
{
    void saveWeather(Weather currentWeather);
    List<Weather> getWeathers();
    Weather getWeatherByID(Long id);
    Weather getCurrentWeather();
}
