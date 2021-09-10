import { Component, Input, OnInit } from '@angular/core';
import { EmployeeDetailsComponent } from '../employee-details/employee-details.component';
import { EmployeeListComponent } from '../employee-list/employee-list.component';


@Component({
  selector: 'app-modal-dialog',
  templateUrl: './modal-dialog.component.html',
  styleUrls: ['./modal-dialog.component.css']
})


export class ModalDialogComponent implements OnInit {

  @Input() employeeName: EmployeeDetailsComponent;
  @Input() employeeLastName: EmployeeDetailsComponent;
  @Input() employeeEmail: EmployeeDetailsComponent;
  @Input() employeeId: EmployeeDetailsComponent;
  @Input() activeModalComponent: boolean;

  constructor() { }

  ngOnInit(): void {
  }
}
