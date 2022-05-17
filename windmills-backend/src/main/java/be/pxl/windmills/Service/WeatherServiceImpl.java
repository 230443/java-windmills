package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Weather;
import be.pxl.windmills.Repository.WeatherRepository;
import org.springframework.stereotype.Service;

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
    public void saveWeather(Weather currentWeather)
    {
        weatherRepository.save(currentWeather);
    }

    @Override
    public List<Weather> getWeathers()
    {
        return weatherRepository.findAll();
    }

    @Override
    public Weather getWeatherByID(Long id)
    {
        return weatherRepository.getById(id);
    }

    @Override
    public Weather getCurrentWeather()
    {
        return null;
    }
}
