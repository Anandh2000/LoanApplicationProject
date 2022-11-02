import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule , ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateLoanComponent } from './create-loan/create-loan.component';
import { LoanHistoryComponent } from './loan-history/loan-history.component';
import { PaymentScheduleComponent } from './payment-schedule/payment-schedule.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { HomeComponent } from './home/home.component';
import { ErrorResponseComponent } from './error-response/error-response.component';
import { SideMenuBarComponent } from './side-menu-bar/side-menu-bar.component';


@NgModule({
  declarations: [
    AppComponent,
    CreateLoanComponent,
    LoanHistoryComponent,
    PaymentScheduleComponent,
    WelcomeComponent,
    HomeComponent,
    ErrorResponseComponent,
    SideMenuBarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
