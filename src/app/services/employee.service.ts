import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Employee } from '../models/employee';
import { catchError } from 'rxjs/operators';

/**
 * @author Cem Tunc AKSUNA - tunCode
 */

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _URL: string = "http://localhost:8080/api/v1/employees";

  constructor(private http: HttpClient) {
  }

  getAllEmployee(params: any): Observable<any> {
    return this.http.get(this._URL, { params })
      .pipe(
        catchError(this.handleError)
      )
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this._URL, employee)
      .pipe(
        catchError(this.handleError),
      )
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this._URL}/${id}`)
      .pipe(
        catchError(this.handleError),
      )
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(this._URL + '/' + id, employee)
      .pipe(
        catchError(this.handleError),
      )
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(this._URL + '/' + id)
      .pipe(
        catchError(this.handleError),
      )
  }

  handleError(_httpError) {
    let errorMessage: string = '';

    if (_httpError.status !== 200) {
      if (_httpError.error instanceof ErrorEvent) {
        errorMessage = `Error : ${_httpError.error.message}`
      } else {
        errorMessage = `
        Error Code : ${_httpError.status}
        Error message : ${_httpError.message}`;
      }
      return throwError(errorMessage);
    }
  }
}
