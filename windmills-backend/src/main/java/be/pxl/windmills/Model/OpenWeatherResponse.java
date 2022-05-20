package be.pxl.windmills.Model;

public class OpenWeatherResponse
{
    private Weather[] weather;
    private Main main;
    private Sys sys;

    public Main getMain()
    {
        return main;
    }

    public void setMain(Main main)
    {
        this.main = main;
    }

    public Sys getSys()
    {
        return sys;
    }

    public void setSys(Sys sys)
    {
        this.sys = sys;
    }

    public Weather[] getWeather()
    {
        return weather;
    }

    public void setWeather(Weather[] weather)
    {
        this.weather = weather;
    }
}
