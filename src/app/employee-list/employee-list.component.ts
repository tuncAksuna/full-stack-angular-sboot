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

  employeeList: Employee[];
  firstName: any;


  employee: any;
  currentEmployee = null;
  currentIndex = -1;

  page = 1;
  count = 0;
  pageSize = 3;
  pageSizeOptions = [3, 5, 10, 15];

  constructor(private employeeService: EmployeeService, private router: Router,) {
  }

  ngOnInit(): void {
    this.getAll();
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
  getAll(): void {
    const params = this.getRequestParam(this.page, this.pageSize);

    this.employeeService.getAllEmployee(params).subscribe(response => {
      const { employees, totalItems } = response;
      this.employee = employees;
      this.count = totalItems;
    },
      error => {
        console.warn(error);
      })
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
      this.employee = this.employee.filter(res => {
        return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      })
    }
  }
}

