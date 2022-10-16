import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Loan } from '../loan-history/loan-history.component';


@Injectable({
  providedIn: 'root'
})
export class WebServiceService {

  constructor(public http:HttpClient) { }

  createLoan(value:any){
    return this.http.post("http://localhost:8080/createNewLoan",value,{responseType: 'text'});
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
