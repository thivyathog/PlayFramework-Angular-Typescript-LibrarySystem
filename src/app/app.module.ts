import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LibItemComponent } from './lib-item/lib-item.component';
import { AddlibItemComponent } from './addlib-item/addlib-item.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ReaderComponent } from './reader/reader.component';
import { ReturnComponent } from './return/return.component';
import { BorrowComponent } from './borrow/borrow.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { DeleteComponent } from './delete/delete.component';
import { ReserveComponent } from './reserve/reserve.component';
import { LoginComponent } from './login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from'./material';
import { ReportComponent } from './report/report.component'




@NgModule({
  declarations: [
    AppComponent,
    LibItemComponent,
    AddlibItemComponent,
    NavbarComponent,
    ReaderComponent,
    ReturnComponent,
    BorrowComponent,
    DeleteComponent,
    ReserveComponent,
    LoginComponent,
    ReportComponent,

   
  
  ],
  imports: [
   
    BrowserModule,
    AppRoutingModule,
   ReactiveFormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MaterialModule
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
