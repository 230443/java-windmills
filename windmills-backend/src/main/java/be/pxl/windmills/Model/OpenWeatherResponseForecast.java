package be.pxl.windmills.Model;

import java.util.List;

public class OpenWeatherResponseForecast
{
    private List<WeatherList> list;

    public List<WeatherList> getList()
    {
        return list;
    }

    public void setList(List<WeatherList> list)
    {
        this.list = list;
    }
}
