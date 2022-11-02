import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { errMessage } from '../create-loan/create-loan.component';
import { Loan } from '../loan-history/loan-history.component';


@Injectable({
  providedIn: 'root'
})
export class WebServiceService {

  constructor(public http:HttpClient) { }

  createLoan(value:any){
    return this.http.post<errMessage>("http://localhost:8080/createNewLoan",value);
  }

  loanHistory(){ 
    return this.http.get<Loan[]>("http://localhost:8080/loanDetails");
  }

  scheduleHistory(id:number){
    return this.http.get<any>(`http://localhost:8080/customer/${id}`);
  }

  payment(id:number){
    return this.http.get<any>(`http://localhost:8080/customer/payment/${id}`)
  }

  
}
