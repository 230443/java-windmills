import { Component, OnInit } from '@angular/core';
import {Location} from "../model/location";
import {LocationService} from "../services/location.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit
{
  location!: Location;

  constructor(private locationService:LocationService)
  { }

  ngOnInit(): void
  {
    this.locationService.getClientLocation().subscribe(
      (data) => {
        this.location = data;
      });
  }
}
