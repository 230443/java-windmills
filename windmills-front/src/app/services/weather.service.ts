import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {CurrentWeather} from "../model/current-weather";
import {Observable} from "rxjs";
import {WeatherForecast} from "../model/weather-forecast";

@Injectable({
  providedIn: 'root'
})
export class WeatherService
{
  private baseUrl = 'http://localhost:8080/weatherAPI/';

  constructor(private http:HttpClient) { }

  getCurrentWeather(lat:String, lon:String): Observable<CurrentWeather>
  {
    const params = new HttpParams().set('lat', lat.toString()).set('lon', lon.toString());

    return this.http.post<CurrentWeather>(this.baseUrl + 'current-weather', "", {'params': params});
  }

  getWeatherForecast(lat:String, lon:String): Observable<WeatherForecast[]>
  {
    const params = new HttpParams().set('lat', lat.toString()).set('lon', lon.toString());
    let temp = this.http.post<WeatherForecast[]>(this.baseUrl + 'weather-forecast', "", {'params': params});
    temp.forEach((element) => {
      console.log(element[0].temp);});
    return this.http.post<WeatherForecast[]>(this.baseUrl + 'weather-forecast', "", {'params': params});
  }
}
