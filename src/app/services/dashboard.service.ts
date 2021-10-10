import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private _URL = 'https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b1b15e88fa797225412429c1c50c122a1';

  constructor(private _http: HttpClient) { }

  getAllUsers(): Observable<any> {
    return this._http.get(this._URL)
  }

}
