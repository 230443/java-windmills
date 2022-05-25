package be.pxl.windmills.Model;

public class WeatherList
{
    private Main main;
    private Weather[] weather;
    private String dt;

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public Weather[] getWeather()
    {
        return weather;
    }

    public void setWeather(Weather[] weather)
    {
        this.weather = weather;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }
}
