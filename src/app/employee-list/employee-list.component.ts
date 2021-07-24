import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees: Employee[];
  firstName: any;
  page: number;
  pageSize: number;

  val: string = '';
  constructor(private employeeService: EmployeeService, private router: Router, private route: ActivatedRoute) { // Injection..
  }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees() {
    this.employeeService.getEmployeeList(this.page, this.pageSize).subscribe(
      data => {
        this.employees = data;
      })
  }


  updateEmployee(id: number) {
    this.router.navigate(["/update-employee", id]);
  }

  deleteEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe(deletedData => {
      this.getEmployees();
      console.log(deletedData);
    })
    error => console.log(error);
  }

  employeeDetails(id: number) {
    this.router.navigate(["/details-employee", id]);
  }

  searchEmployeeByFirstName() {
    if (this.firstName == "") {
      this.ngOnInit();
    } else {
      this.employees = this.employees.filter(res => {
        return res.firstName.toLocaleLowerCase().match(this.firstName.toLocaleLowerCase());
      })
    }
  }
}
