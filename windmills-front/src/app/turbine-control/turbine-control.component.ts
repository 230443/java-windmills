import { Component, OnInit } from '@angular/core';
import windmillIcon from '@iconify/icons-icon-park-solid/windmill-two';
import windSpeed from '@iconify/icons-fluent/weather-squalls-24-filled';
import {ThemePalette} from "@angular/material/core";

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
  smallIcon = "smallIcon";

  constructor() { }

  ngOnInit(): void {
  }

  public onToggle() {
    if(this.isToggleChecked)
    {
      this.iconColor = this.ICON_COLOR;
    }
    else
    {
      this.iconColor = this.ICON_COLOR_OFF;
    }
  }
}
