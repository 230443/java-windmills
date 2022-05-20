import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {CurrentWeather} from "../model/current-weather";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CurrentWeatherService
{
  private baseUrl = 'http://localhost:8080/weatherAPI/';

  constructor(private http:HttpClient) { }

  getCurrentWeather(lat:String, lon:String): Observable<CurrentWeather>
  {
    const params = new HttpParams().set('lat', lat.toString()).set('lon', lon.toString());

    return this.http.post<CurrentWeather>(this.baseUrl + 'current-weather', "", {'params': params});
  }
}
