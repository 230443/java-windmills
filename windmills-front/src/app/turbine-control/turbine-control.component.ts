import { Component, OnInit } from '@angular/core';
import windmillTwo from '@iconify/icons-icon-park-solid/windmill-two';
import {ThemePalette} from "@angular/material/core";

@Component({
  selector: 'app-turbine-control',
  templateUrl: './turbine-control.component.html',
  styleUrls: ['./turbine-control.component.css']
})
export class TurbineControlComponent implements OnInit {

  ICON_COLOR_OFF = "#FF4D4D";
  ICON_COLOR_ON = "#6165F7";

  windmillIcon = windmillTwo;
  iconColor = this.ICON_COLOR_OFF;
  toggleColor: ThemePalette = 'primary';
  isToggleChecked = false;
  windmill = "windmill";

  constructor() { }

  ngOnInit(): void {
  }

  public onToggle() {
    if(this.isToggleChecked)
    {
      this.iconColor = this.ICON_COLOR_ON;
    }
    else
    {
      this.iconColor = this.ICON_COLOR_OFF;
    }
  }
}
