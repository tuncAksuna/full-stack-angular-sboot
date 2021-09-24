import { Component, Input, OnInit } from '@angular/core';
import { FileData } from '../models/filedata';
import { FileTransactionsService } from '../services/file-transactions-service';

@Component({
  selector: 'app-sorting',
  templateUrl: './sorting.component.html',
  styleUrls: ['./sorting.component.css']
})
export class SortingComponent implements OnInit {

  constructor(private fileService: FileTransactionsService) { }

  @Input() fileList: FileData[];

  ngOnInit(): void {
    console.log();
  }

  filesSortBySizeASC() {
    console.log("HIIII")
    this.fileService.listFilesOrderBySizeASC()
      .subscribe(results => {
        this.fileList = results;
        console.log(this.fileList);
        console.log(results)
      })
  }
}
