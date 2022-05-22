import {Component, EventEmitter, OnInit, Output} from '@angular/core';

import weatherPartlyCloudy from '@iconify/icons-fluent/weather-partly-cloudy-day-24-filled';
import weatherRainy from '@iconify/icons-fluent/weather-rain-showers-day-24-filled';
import weatherSunny from '@iconify/icons-fluent/weather-sunny-16-filled';
import weatherSnowy from '@iconify/icons-fluent/weather-snowflake-20-filled';
import {WeatherService} from "../../services/weather.service";
import {WeatherForecast} from "../../model/weather-forecast";
import {LocationService} from "../../services/location.service";
import {Location} from "../../model/location";
import weatherCloudy from "@iconify/icons-fluent/weather-cloudy-24-filled";
import weatherFoggy from "@iconify/icons-fluent/weather-haze-24-filled";

@Component({
  selector: 'app-weather-forecast',
  templateUrl: './weather-forecast.component.html',
  styleUrls: ['./weather-forecast.component.css']
})
export class WeatherForecastComponent implements OnInit
{
  ICON_COLOR = "#6165F7";
  SUN_ICON_COLOR = "#FFD24C";

  weatherForecast!:WeatherForecast[];
  location!:Location;

  smallIcon = "smallIcon";

  constructor(private locationService:LocationService, private weatherForecastService:WeatherService)
  {
    this.locationService.getClientLocation().subscribe((data: any) =>{
      this.location = data;
    })
  }

  ngOnInit(): void
  {
    this.weatherForecastService.getWeatherForecast(this.location.latitude, this.location.longitude).subscribe((data: any) =>{
      this.weatherForecast = data;
      this.weatherForecast.forEach((element) => {
        element.date = new Date(+element.dt*1000);
      })
    })
  }

  getIconClass(icon: String)
  {
    // icon list available on https://openweathermap.org/weather-conditions
    if (icon === '01d' || icon === '01d') {
      return weatherSunny;
    }
    else
    {
      if (icon === '03d' || icon === '03n' || icon === '04d' || icon === '04n') {
        return weatherCloudy;
      } else if (icon === '09d' || icon === '09n' || icon === '10d' || icon === '10n' || icon === '11d' || icon === '11n') {
        return weatherRainy;
      } else if (icon === '13d' || icon === '13n') {
        return weatherSnowy;
      } else if (icon === '50d' || icon === '50n') {
        return weatherFoggy;
      } else {
        return weatherPartlyCloudy;
      }
    }
  }

  getIconColor(icon: String)
  {
    if (icon === '01d' || icon === '01d') {
      return this.SUN_ICON_COLOR;
    }
    else
    {
      return this.ICON_COLOR;
    }
  }
}
