import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { FileData } from 'src/app/models/filedata';
import { catchError, retry } from 'rxjs/operators';

/**
 * @author Cem Tunc AKSUNA - tuncOde
 */

@Injectable({
  providedIn: 'root'
})

export class FileTransactionsService {

  private _URLFile: string = "http://localhost:8080/api/transactions/files";

  constructor(private http: HttpClient) {
  }

  onUpload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    const request = new HttpRequest('POST', `${this._URLFile}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(request)
      .pipe(
        retry(3),
        catchError(this.handleError),
      )
  }

  listFiles(): Observable<FileData[]> {
    return this.http.get<FileData[]>(`${this._URLFile}/downloadAllFiles`)
      .pipe(
        retry(3),
        catchError(this.handleError),
      )
  }

  listFilesOrderByDESC(): Observable<FileData[]> {
    return this.http.get<FileData[]>(`${this._URLFile}/downloadOrderByDataDESC`)
      .pipe(
        retry(3),
        catchError(this.handleError)
      )
  }

  listFilesOrderByASC(): Observable<FileData[]> {
    return this.http.get<FileData[]>(`${this._URLFile}/downloadOrderByDataASC`)
      .pipe(
        retry(3),
        catchError(this.handleError)
      )
  }

  download(fileId: string | undefined): Observable<Blob> {

    return this.http.get(`${this._URLFile}/download/${fileId}`, {
      responseType: 'blob',
    }
    )
      .pipe(
        retry(3),
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
        Error Code :    ${_httpError.status}
        Error message : ${_httpError.message}`;
      }
      return throwError(errorMessage);
    }
  }
}
