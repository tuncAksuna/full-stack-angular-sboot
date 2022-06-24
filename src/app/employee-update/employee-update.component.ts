import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../models/employee';
import { OpenDialogComponent } from '../open-dialog/open-dialog.component';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private router: Router, public dialog: MatDialog) { }

  employee: Employee = new Employee();

  id: number;

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id)
      .subscribe(data => {
        this.employee = data;
      }),
      error => console.log(error);
  }

  goToEmployeeList() {
    return this.router.navigate(["/employees"]);
  }

  updateEmployeeInfo = new FormGroup({
    firstName: new FormControl('',
      [
        Validators.required,
        Validators.maxLength(13),
      ]),
    lastName: new FormControl('',
      [
        Validators.required,
        Validators.maxLength(20),
      ]
    ),
    emailID: new FormControl('',
      [
        Validators.required,
        Validators.email,
      ]
    ),
  })

  openDialog() {
    this.dialog.open(OpenDialogComponent);
  }

  updatedEmployee() {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data => {
      this.openDialog();
      console.log(`${this.employee.firstName} is successfully updated..`)
      this.goToEmployeeList();
    }),
      error => console.log(error);
  }

  get firstName() {
    return this.updateEmployeeInfo.get("firstName");
  }

  get lastName() {
    return this.updateEmployeeInfo.get("lastName");
  }

  get emailID() {
    return this.updateEmployeeInfo.get("emailID");
  }

}
