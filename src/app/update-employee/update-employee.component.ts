import { Component, ErrorHandler, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { error } from 'selenium-webdriver';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private router: Router) { }

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

  updatedEmployee() {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data => {
        this.goToEmployeeList();
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
