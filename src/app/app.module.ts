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
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeListComponent,
    EmployeeCreateComponent,
    EmployeeDetailsComponent,
    FileTransactionsComponent,
    EmployeeUpdateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Ng2SearchPipeModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
