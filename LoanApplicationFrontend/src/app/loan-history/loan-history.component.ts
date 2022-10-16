import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebServiceService } from '../service/web-service.service';

export class Loan{
  constructor(
    public customerId:number,
    public loanAmount:number,
    public loanStartDate:Date,
    public maturityDate:Date,
    public paymentTerm:string
  )
  {
  }
}
@Component({
  selector: 'app-loan-history',
  templateUrl: './loan-history.component.html',
  styleUrls: ['./loan-history.component.css']
})
export class LoanHistoryComponent implements OnInit {
  page:number=1;
  count:number=0;
  tableSize:number=10;
  tableSizes:any=[5,10,15,20]
  loans:Loan[] =[]
  constructor(private service:WebServiceService,private router: Router) { }

  ngOnInit(): void {
    this.loanList()
  }
  loanList(){
    this.service.loanHistory().subscribe(
      (response) => {
         console.log(response)
        this.loans = response}
     )
  }
  onTableDataChange(event:any){
    this.page=event;
    this.loanList();
  }

  onTableSizeChange(event:any):void{
    this.tableSize=event.target.value;
    this.page=1;
    this.loanList();
  }

  onConfirm(id:number){
    console.log(id)
    this.router.navigate(['paymentSchedule',id])

    //this.service.scheduleHistory
  }

}
