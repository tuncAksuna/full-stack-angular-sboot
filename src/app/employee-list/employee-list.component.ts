import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[];
  firstName: any;

  page: number = 0;
  pageSize: number = 5;

  constructor(private employeeService: EmployeeService, private router: Router,) {
  }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.employeeService.getEmployees(this.page, this.pageSize).subscribe(
      data => {
        this.employees = data;
      })
  }

  updateEmployee(id: number) {
    this.router.navigate(["/update-employee", id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(deletedData => {
      this.getAll();
      console.log(deletedData);
    })
    error => console.log(error);
  }

  employeeDetails(id: number) {
    this.router.navigate(["/details-employee", id]);
  }

  searchEmployeeByFirstName() {
    if (this.firstName == "") {
      this.getAll();
    } else {
      this.employees = this.employees.filter(res => {
        return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      })
    }
  }
}

