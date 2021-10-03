import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  constructor(
    private employeeService: EmployeeService, private router: Router,) { }

  ngOnInit(): void {
  }

  employee: Employee = new Employee();

  onSubmit() {
    this.createUserInfos.reset();   // to clean input
  }

  // reactive form -- 
  createUserInfos = new FormGroup({
    firstName: new FormControl('',
      [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(13),
      ]),
    lastName: new FormControl('',
      [
        Validators.required,
        Validators.minLength(3),
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
    return this.createUserInfos.get("firstName");
  }

  get lastName() {
    return this.createUserInfos.get("lastName");
  }

  get emailID() {
    return this.createUserInfos.get("emailID");
  }
  // -- reactive form

  // -- route..
  refreshThePage() {
    this.router.navigateByUrl("/create-employee", {
      skipLocationChange: true
    }).then(() => {
      this.router.navigate(["/create-employee"])
    });
  }

  saveEmployee() {
    this.employeeService.createEmployee(this.employee)
      .subscribe(data => {
        console.log(data);
        this.refreshThePage();
      },
        error => console.error(error)
      )
  }

}
