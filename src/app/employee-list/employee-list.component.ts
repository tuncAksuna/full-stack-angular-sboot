import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import * as fileSaver from 'file-saver';
import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PageEvent } from '@angular/material/paginator';

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
  totalElements: number = 0; // for pagination

  constructor(private employeeService: EmployeeService,
    private router: Router,) { // Injection..
  }

  // nextPage(event: PageEvent) {
  //   const request = {};
  //   request['page'] = event.pageIndex.toString();
  //   request['pageSize'] = event.pageSize.toString();
  //   this.getAllEmployees(request);
  // }

  // private getAllEmployees(request) {

  //   this.employeeService.getEmployeeList(request)
  //     .subscribe(data => {
  //       this.employees = data['content'];
  //       this.totalElements = data['totalElements'];
  //     });
  // }

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

