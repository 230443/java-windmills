import { Component, OnInit } from '@angular/core';
import {LocationService} from "../../services/location.service";
import {Location} from "../../model/location";

@Component({
  selector: 'app-weather-info',
  templateUrl: './weather-info.component.html',
  styleUrls: ['./weather-info.component.css']
})
export class WeatherInfoComponent implements OnInit
{
  location!: Location;

  constructor(private locationService:LocationService)
  {
    this.locationService.getClientLocation().subscribe(
      (data) => {
        this.location = data;
      });
  }

  ngOnInit(): void
  { }
}
