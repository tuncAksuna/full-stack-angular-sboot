import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { saveAs } from 'file-saver';
import { FileData } from 'src/app/models/filedata';
import { FileTransactionsService } from '../services/file-transactions-service';

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

  ngOnInit(): void {
    this.getFileList();
  }

  getFileList(): void {
    this.fileService.listFiles().subscribe(results => {
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
    this.message = '';
    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
    }
  }
}
