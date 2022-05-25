import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TurbineStatus} from "../model/turbine-status";
import {Observable} from "rxjs";

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

  getSpeed(): Observable<TurbineStatus>
  {
    return this.http.get<TurbineStatus>(this.baseUrl + 'status/now');
  }
}
