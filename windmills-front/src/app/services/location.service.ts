import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, retry} from "rxjs";
import {Location} from "../model/location";

@Injectable({
  providedIn: 'root'
})
export class LocationService
{
  private baseUrl = 'http://localhost:8080/locationAPI/';

  constructor(private http:HttpClient) { }

  getClientLocation(): Observable<Location>
  {
    return this.http.get<Location>(this.baseUrl + 'get-location').pipe(retry(3));
  }
}
