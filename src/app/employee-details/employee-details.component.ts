import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';

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
