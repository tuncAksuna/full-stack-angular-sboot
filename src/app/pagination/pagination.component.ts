import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {


  currentIndex: number = -1;

  @Input() page: number;
  @Input() pageSize: number;
  @Input() pageSizeOptions: number[];


  constructor() { }

  ngOnInit(): void {
  }





}
