import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {CurrentWeather} from "../model/current-weather";
import {Observable, retry} from "rxjs";
import {WeatherForecast} from "../model/weather-forecast";

@Injectable({
  providedIn: 'root'
})
export class WeatherService
{
  private baseUrl = 'http://localhost:8080/weatherAPI/';

  constructor(private http:HttpClient) { }

  getCurrentWeather(lat:String, lon:String)
  {
    const params = new HttpParams().set('lat', lat.toString()).set('lon', lon.toString());

    return this.http.post<CurrentWeather>(this.baseUrl + 'current-weather', "", {'params': params}).pipe(retry(3));
  }

  getWeatherForecast(lat:String, lon:String): Observable<WeatherForecast[]>
  {
    const params = new HttpParams().set('lat', lat.toString()).set('lon', lon.toString());

    return this.http.post<WeatherForecast[]>(this.baseUrl + 'weather-forecast', "", {'params': params});
  }
}
