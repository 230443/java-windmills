package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Weather;

import java.util.List;

public interface WeatherService
{
    public void saveWeather(Weather currentWeather);
    public List<Weather> getWeathers();
    public Weather getWeatherByID(Long id);
}
