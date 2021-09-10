import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../models/employee';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  employee: Employee;
  employees: Employee[] = [];
  activeModalComponent: boolean = false;

  constructor(private _router: ActivatedRoute, private employeeService: EmployeeService, private _route: Router) { }

  ngOnInit(): void {
    this.id = this._router.snapshot.params['id'];

    this.employeeService.getEmployeeById(this.id).subscribe(empl => {
      this.employees.push(empl);
    })

    this.employee = new Employee();
    this.employeeService.getEmployeeById(this.id).subscribe(
      data => {
        this.employee = data;
        console.log(this.employee)
      }
    )
    error => console.log(error);
  }

  activeModal() {
    this.activeModalComponent = true;
  }


  goToEmployeeUpdate() {
    this._route.navigate(["/update-employee", this.id]);
    console.log(this.id)
  }

}
