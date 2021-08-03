import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { EmployeeService } from '../employee.service';
import { saveAs } from 'file-saver';
import { FileData } from 'src/app/filedata';
import { type } from 'node:os';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-file-transactions',
  templateUrl: './file-transactions.component.html',
  styleUrls: ['./file-transactions.component.css']
})
export class FileTransactionsComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute) { }

  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  fileInfos: Observable<any>;
  fileList?: FileData[];

 

  ngOnInit(): void {
    this.getFileList();
  }

  getFileList(): void {
    this.employeeService.listFiles().subscribe(results => {
      this.fileList = results;
    })
  }

  downloadFile(fileData: FileData) {
    this.employeeService.download(fileData.name).subscribe(blob => {
      saveAs(blob, fileData.name);
    })
  }

  upload(idx, file): void {
    this.progressInfos[idx] = { value: 0, fileName: file.name };

    this.employeeService.onUpload(file).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.getFileList();
        }
      },
      err => {
        this.progressInfos[idx].value = 0;
        this.message = 'Could not upload the file, please check extensions(not acceptable .xlsx) , File :  ' + file.name;
      });
  }

  selectFiles(event): void {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

  uploadFiles(): void {
    this.message = '';
    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
    }
  }
}
