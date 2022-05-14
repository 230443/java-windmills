import { Component, OnInit } from '@angular/core';
import weatherPartlyCloudy from '@iconify/icons-fluent/weather-partly-cloudy-day-24-filled';
import weatherRainy from '@iconify/icons-fluent/weather-rain-showers-day-24-filled';
import weatherSunny from '@iconify/icons-fluent/weather-sunny-16-filled';
import weatherSnowy from '@iconify/icons-fluent/weather-snowflake-20-filled';

import humidity from '@iconify/icons-fluent/drop-20-filled';
import cloudiness from '@iconify/icons-fluent/weather-cloudy-20-filled';
import pressure from '@iconify/icons-fluent/arrow-minimize-vertical-20-filled';
import sunriseSunset from '@iconify/icons-fluent/weather-sunny-low-24-filled';

@Component({
  selector: 'app-current-weather',
  templateUrl: './current-weather.component.html',
  styleUrls: ['./current-weather.component.css']
})
export class CurrentWeatherComponent implements OnInit
{
  ICON_COLOR = "#6165F7"

  todayIcon = weatherPartlyCloudy;
  today = "todayIcon";

  humidityIcon = humidity;
  cloudinessIcon = cloudiness;
  pressureIcon = pressure;
  sunriseSunsetIcon = sunriseSunset;
  smallIcon = "smallIcon";

  constructor() { }

  ngOnInit(): void {
  }

}
