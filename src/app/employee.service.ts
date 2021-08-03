import { HttpClient, HttpEvent, HttpRequest, HttpResponse, } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { Employee } from './employee';
import { FileData } from 'src/app/filedata';


@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private _URL: string = "http://localhost:8080/api/v1/employees";
  private _URLFile: string = "http://localhost:8080/api/transactions/files";

  constructor(private http: HttpClient) {
    // Injection 
  }

  getEmployeeList(page: number, size: number): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this._URL}/${page}/${size}`)
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

  onUpload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const request = new HttpRequest('POST', `${this._URLFile}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    })

    return this.http.request(request);
  }

  listFiles(): Observable<FileData[]> {
    return this.http.get<FileData[]>(`${this._URLFile}/downloadAllFiles`);
  }

  // downloadFile(fileId: any): Observable<Blob> {
  //   return this.http.get(`${this._URLFile}/download/${fileId}`, {
  //     reportProgress: true,
  //     responseType: 'blob'
  //   });
  // }

  download(fileId: any): any {
    return this.http.get(`${this._URLFile}/download/${fileId}`, { responseType: 'blob' });
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
