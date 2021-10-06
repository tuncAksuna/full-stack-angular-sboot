import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { FileTransactionsComponent } from './file-transactions/file-transactions.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { EmployeeService } from './services/employee.service';
import { FileTransactionsService } from './services/file-transactions-service';
import { ModalDialogComponent } from './modal-dialog/modal-dialog.component';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from './material.module';
import { PaginationComponent } from './pagination/pagination.component';
import { OpenDialogComponent } from './open-dialog/open-dialog.component';



@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    EmployeeCreateComponent,
    EmployeeDetailsComponent,
    FileTransactionsComponent,
    EmployeeUpdateComponent,
    ModalDialogComponent,
    PaginationComponent,
    OpenDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    BrowserAnimationsModule,
    NgxPaginationModule,
    AngularMaterialModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [EmployeeService, FileTransactionsService],
  bootstrap: [AppComponent]
})

export class AppModule { }
