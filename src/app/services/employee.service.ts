import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Employee } from '../models/employee';
import { catchError, retry } from 'rxjs/operators';

/**
 * @author Cem Tunc AKSUNA - tuncOde
 */

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _URL: string = "http://localhost:8080/api/v1/employees";

  constructor(private http: HttpClient) {
  }

  getEmployees(page: number, pageSize: number): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this._URL}/${page}/${pageSize}`)
      .pipe(
        retry(3),
        catchError(this.handleHttpResponse),
      )
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this._URL, employee)
      .pipe(
        retry(3),
        catchError(this.handleHttpResponse),
      )
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this._URL}/${id}`)
      .pipe(
        retry(3),
        catchError(this.handleHttpResponse),
      )
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(this._URL + '/' + id, employee)
      .pipe(
        retry(3),
        catchError(this.handleHttpResponse),
      )
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(this._URL + '/' + id)
      .pipe(
        retry(3),
        catchError(this.handleHttpResponse),
      )
  }

  handleHttpResponse(_httpError) {
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
