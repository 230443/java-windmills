import { Component, OnInit } from '@angular/core';
import weatherSunny from "@iconify/icons-fluent/weather-sunny-16-filled";
import weatherCloudy from "@iconify/icons-fluent/weather-cloudy-24-filled";
import weatherRainy from "@iconify/icons-fluent/weather-rain-showers-day-24-filled";
import weatherSnowy from "@iconify/icons-fluent/weather-snowflake-20-filled";
import weatherFoggy from "@iconify/icons-fluent/weather-haze-24-filled";
import weatherPartlyCloudy from "@iconify/icons-fluent/weather-partly-cloudy-day-24-filled";

@Component({
  selector: 'app-weather-info',
  templateUrl: './weather-info.component.html',
  styleUrls: ['./weather-info.component.css']
})
export class WeatherInfoComponent implements OnInit
{
  constructor() { }

  ngOnInit(): void {
  }
}
