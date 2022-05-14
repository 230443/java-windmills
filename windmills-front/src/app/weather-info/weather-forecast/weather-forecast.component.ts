import { Component, OnInit } from '@angular/core';

import weatherPartlyCloudy from '@iconify/icons-fluent/weather-partly-cloudy-day-24-filled';
import weatherRainy from '@iconify/icons-fluent/weather-rain-showers-day-24-filled';
import weatherSunny from '@iconify/icons-fluent/weather-sunny-16-filled';
import weatherSnowy from '@iconify/icons-fluent/weather-snowflake-20-filled';

@Component({
  selector: 'app-weather-forecast',
  templateUrl: './weather-forecast.component.html',
  styleUrls: ['./weather-forecast.component.css']
})
export class WeatherForecastComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
