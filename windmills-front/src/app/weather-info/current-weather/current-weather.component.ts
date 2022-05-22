import { Component, OnInit } from '@angular/core';
import weatherPartlyCloudy from '@iconify/icons-fluent/weather-partly-cloudy-day-24-filled';
import weatherCloudy from '@iconify/icons-fluent/weather-cloudy-24-filled';
import weatherRainy from '@iconify/icons-fluent/weather-rain-showers-day-24-filled';
import weatherSunny from '@iconify/icons-fluent/weather-sunny-16-filled';
import weatherSnowy from '@iconify/icons-fluent/weather-snowflake-20-filled';
import weatherFoggy from '@iconify/icons-fluent/weather-haze-24-filled';

import humidity from '@iconify/icons-fluent/drop-20-filled';
import cloudiness from '@iconify/icons-fluent/weather-cloudy-20-filled';
import pressure from '@iconify/icons-fluent/arrow-minimize-vertical-20-filled';
import sunriseSunset from '@iconify/icons-fluent/weather-sunny-low-24-filled';
import {CurrentWeather} from "../../model/current-weather";
import {Location} from "../../model/location";
import {WeatherService} from "../../services/weather.service";
import {LocationService} from "../../services/location.service";

@Component({
  selector: 'app-current-weather',
  templateUrl: './current-weather.component.html',
  styleUrls: ['./current-weather.component.css']
})
export class CurrentWeatherComponent implements OnInit
{
  ICON_COLOR = "#6165F7";
  SUN_ICON_COLOR = "#FFD24C";

  today = "todayIcon";

  humidityIcon = humidity;
  cloudinessIcon = cloudiness;
  pressureIcon = pressure;
  sunriseSunsetIcon = sunriseSunset;
  smallIcon = "smallIcon";

  currentWeather!:CurrentWeather;
  location!:Location;
  sunriseDate!:Date;
  sunsetDate!:Date;

  constructor(private locationService:LocationService, private currentWeatherService:WeatherService)
  {
    this.locationService.getClientLocation().subscribe((data: any) =>{
      this.location = data;
    })
  }

  ngOnInit(): void
  {
    this.currentWeatherService.getCurrentWeather(this.location.latitude, this.location.longitude).subscribe((data: any) =>{
      this.currentWeather = data;
      this.sunriseDate = new Date(+this.currentWeather.sunrise*1000);
      this.sunsetDate = new Date(+this.currentWeather.sunset*1000);
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
