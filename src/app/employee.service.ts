import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _URL: string = "http://localhost:8080/api/v1/employees";

  constructor(private http: HttpClient) {
    // Injection 
  }

  getEmployeeList(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this._URL);
  }

  createEmployee(employee: Employee): Observable<Object> {
    return this.http.post(this._URL, employee);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this._URL}/${id}`)
  }

  updateEmployee(id: number, employee: Employee): Observable<Object> {
    return this.http.put(this._URL + '/' + id, employee);
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(this._URL + '/' + id);
  }

}

