import { Component, Input, OnInit } from '@angular/core';
import { EmployeeListComponent } from '../employee-list/employee-list.component';


declare var $: any;

@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styleUrls: ['./modal-dialog.component.css']
})


export class ModalDialogComponent implements OnInit {

  @Input() employeeName: EmployeeListComponent | string;
  @Input() employeeLastName: EmployeeListComponent | string;
  @Input() employeeEmail: EmployeeListComponent | string;
  @Input() activeModalComponent;

  constructor() { }


  ngOnInit(): void {

  }
}
