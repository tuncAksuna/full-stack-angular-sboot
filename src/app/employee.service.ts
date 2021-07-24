import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Employee } from './employee';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _URL: string = "http://localhost:8080/api/v1/employees";

  constructor(private http: HttpClient) {
    // Injection 
  }

  getEmployeeList(page: number, size: number): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this._URL}/${page}/${size}`)
  }

  getAllEmployeesPaginate(): Observable<Employee[]> {
    const _paginateURL = `${this._URL}/pagination`;
    return this.http.get<any>(_paginateURL)
      .pipe(
        map(resp => resp.json()),
        catchError(this.handleError)
      )
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this._URL, employee);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this._URL}/${id}`)
  }

  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(this._URL + '/' + id, employee);
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.http.delete(this._URL + '/' + id);
  }


  handleError(_httpError) {
    let errorMessage: string = '';

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
