import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CurrentWeather} from "../model/current-weather";

@Injectable({
  providedIn: 'root'
})
export class TurbineService
{
  private baseUrl = 'http://localhost:5000/';

  constructor(private http:HttpClient) { }

  turnTurbineOff(): void
  {
    this.http.post(this.baseUrl + 'status', {"isActive": false});
  }

  turnTurbineOn(): void
  {
    this.http.post(this.baseUrl + 'status', {"isActive": true});
  }
}
