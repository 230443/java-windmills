package be.pxl.windmills.Model;

public class WeatherForecast
{
    private String dt;
    private int temp;
    private String icon;

    public WeatherForecast(String dt, int temp, String icon)
    {
        this.dt = dt;
        this.temp = temp;
        this.icon = icon;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }

    public int getTemp()
    {
        return temp;
    }

    public void setTemp(int temp)
    {
        this.temp = temp;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
