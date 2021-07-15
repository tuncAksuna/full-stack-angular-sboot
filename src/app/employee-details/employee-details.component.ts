import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { error } from 'selenium-webdriver';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  constructor(private _route: ActivatedRoute, private employeeService: EmployeeService) { }

  id: number;
  employee: Employee;

  ngOnInit(): void {
    this.id = this._route.snapshot.params['id'];

    this.employee = new Employee();
    this.employeeService.getEmployeeById(this.id).subscribe(
      data => {
        this.employee = data;
      }
    )
    error => console.log(error);
  }

}
