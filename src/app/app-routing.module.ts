import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { FileTransactionsComponent } from './file-transactions/file-transactions.component';

const routes: Routes = [
 
  {
    path: "employees",
    component: EmployeeListComponent
  },
  {
    path: "file-transactions",
    component: FileTransactionsComponent
  },
  {
    path: "dashboard",
    component: DashboardComponent
  },
  {
    path: "create-employee",
    component: EmployeeCreateComponent
  },
  {
    path: "update-employee/:id",
    component: EmployeeUpdateComponent
  },
  {
    path: "details-employee/:id",
    component: EmployeeDetailsComponent
  },
  {
    path: '**', redirectTo: 'employees', pathMatch: "full"
  },
  {
    path: '', redirectTo: 'employees', pathMatch: "full"
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
