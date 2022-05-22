import { Component, OnInit } from '@angular/core';
import windmillIcon from '@iconify/icons-icon-park-solid/windmill-two';
import windSpeed from '@iconify/icons-fluent/weather-squalls-24-filled';
import turbineSpeed from '@iconify/icons-mdi/fan';
import {ThemePalette} from "@angular/material/core";
import {TurbineService} from "../services/turbine.service";
import {TurbineStatus} from "../model/turbine-status";
import { interval } from 'rxjs';

@Component({
  selector: 'app-turbine-control',
  templateUrl: './turbine-control.component.html',
  styleUrls: ['./turbine-control.component.css']
})
export class TurbineControlComponent implements OnInit {

  ICON_COLOR_OFF = "#FF4D4D";
  ICON_COLOR = "#6165F7";

  windmillIcon = windmillIcon;
  iconColor = this.ICON_COLOR_OFF;
  toggleColor: ThemePalette = 'primary';
  isToggleChecked = false;
  windmill = "windmill";

  windSpeedIcon = windSpeed;
  turbineSpeedIcon = turbineSpeed;
  smallIcon = "smallIcon";

  turbineStatus!:TurbineStatus;

  constructor(private turbineService:TurbineService) { }

  ngOnInit(): void
  {
    interval(3000).subscribe((x: any) => { // will execute every 3 seconds
      this.turbineService.getSpeed().subscribe((data: any) =>{
        this.turbineStatus = data;
      })
    });

  }

  public onToggle()
  {
    if(this.isToggleChecked)
    {
      this.iconColor = this.ICON_COLOR;
      this.turbineService.turnTurbineOn();
    }
    else
    {
      this.iconColor = this.ICON_COLOR_OFF;
      this.turbineService.turnTurbineOff();
    }
  }
}
