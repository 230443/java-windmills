import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WeatherInfoComponent } from './dashboard/weather-info/weather-info.component';
import { TurbineControlComponent } from './dashboard/turbine-control/turbine-control.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatButtonModule} from "@angular/material/button";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import { CurrentWeatherComponent } from './dashboard/weather-info/current-weather/current-weather.component';
import { WeatherForecastComponent } from './dashboard/weather-info/weather-forecast/weather-forecast.component';
import {IconModule} from "@visurel/iconify-angular";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {FormsModule} from "@angular/forms";
import { MenuComponent } from './menu/menu.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    WeatherInfoComponent,
    TurbineControlComponent,
    CurrentWeatherComponent,
    WeatherForecastComponent,
    MenuComponent,
    DashboardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatButtonToggleModule,
    IconModule,
    MatSlideToggleModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
