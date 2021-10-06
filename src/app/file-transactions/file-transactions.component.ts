import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { saveAs } from 'file-saver';
import { FileData } from 'src/app/models/filedata';
import { FileTransactionsService } from '../services/file-transactions-service';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-file-transactions',
  templateUrl: './file-transactions.component.html',
  styleUrls: ['./file-transactions.component.css']
})
export class FileTransactionsComponent implements OnInit {

  constructor(private fileService: FileTransactionsService, private route: ActivatedRoute) { }

  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  fileInfos: Observable<any>;
  fileList?: FileData[];

  page: number = 1;
  count: number = 0;
  pageSize: number = 5;
  pageSizeOptions: number[] = [5, 10, 15, 20];

  ngOnInit(): void {
    this.getAll();
  }

  getFileList(): void {
    const params = this.getRequestParam(this.page, this.pageSize);
    this.fileService.listFiles(params).subscribe(results => {
      this.fileList = results;
    })
  }

  downloadFile(fileData: FileData) {
    this.fileService.download(fileData.id).subscribe(blob => {
      saveAs(blob, fileData.name);
    })
  }

  upload(idx, file): void {
    this.progressInfos[idx] = { value: 0, fileName: file.name };

    this.fileService.onUpload(file).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.getFileList();
        }
      },
      err => {
        this.progressInfos[idx].value = 0;
        this.message = 'Could not upload the file, Is your file in .xlsx format ? ' + file.name;
      });
  }

  selectFiles(event): void {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

  uploadFiles(): void {
    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
      this.message = 'File uploaded to the system'
    }
  }

  //* ORDERING
  getFileListOrderBySizeASC() {
    this.fileService.listFilesOrderBySizeASC().subscribe(results => {
      this.fileList = results;
    })
  }

  getFileListOrderBySizeDESC() {
    this.fileService.listFilesOrderBySizeDESC().subscribe(results => {
      this.fileList = results;
    })
  }

  getFileListOrderByUploadedTimeASC() {
    this.fileService.listFilesOrderByUploadedTimeASC().subscribe(results => {
      this.fileList = results;
    })

  }

  getFileListOrderByUploadedTimeDESC() {
    this.fileService.listFilesOrderByUploadedTimeDESC().subscribe(results => {
      this.fileList = results;
    })
  }

  //* ORDERING

  getAll(): void {
    const params = this.getRequestParam(this.page, this.pageSize);

    this.fileService.listFiles(params).subscribe(response => {
      const { files, totalItems } = response;
      this.fileList = files;
      this.count = totalItems;
    },
      error => {
        console.warn("Pagination error in file-operations page : ", error)
      })
  }

  getRequestParam(page, pageSize): any {
    let params = {};

    if (page) {
      params[`page`] = page - 1;
    }

    if (pageSize) {
      params[`size`] = pageSize;
    }

    return params;
  }

  handlePageChange(event): void {
    this.page = event;
    this.getAll();
  }

  handlePageSizeChange(event): void {
    this.pageSize = event.target.value;
    this.page = 1;
    this.getAll();
  }
}
