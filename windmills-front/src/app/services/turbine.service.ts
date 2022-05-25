import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { TurbineStatus } from "../model/turbine-status";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TurbineService {
  private baseUrl = 'http://192.168.0.178:8080/';
  body!: TurbineStatus;

  constructor(private http: HttpClient) { }

  turnTurbineOff(): void {
    this.http.post(this.baseUrl + 'status', { isActive: false }).subscribe(_ => { });
  }

  turnTurbineOn(): void {
    this.http.post(this.baseUrl + 'status', { isActive: true }).subscribe(_ => { });
  }

  getSpeed(): Observable<TurbineStatus> {
    return this.http.get<TurbineStatus>(this.baseUrl + 'status/now');
  }
}
