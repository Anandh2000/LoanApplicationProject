import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateLoanComponent } from './create-loan/create-loan.component';
import { ErrorResponseComponent } from './error-response/error-response.component';
import { HomeComponent } from './home/home.component';
import { LoanHistoryComponent } from './loan-history/loan-history.component';
import { PaymentScheduleComponent } from './payment-schedule/payment-schedule.component';
import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  {
    path:'',redirectTo:'welcome',pathMatch:'full'
  },
  {
    path: 'welcome', component:WelcomeComponent
  },
  {
    path:"home",component:HomeComponent
  },
  {
    path:"create",component:CreateLoanComponent
  },
  {
    path:"loanDetails",component:LoanHistoryComponent
  },
  {
    path:"paymentSchedule/:id",component:PaymentScheduleComponent
  },
  {
    path:"**",component:ErrorResponseComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
