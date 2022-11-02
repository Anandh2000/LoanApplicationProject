import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup ,FormBuilder, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { WebServiceService } from '../service/web-service.service';


export class errMessage{
  constructor(
    public customerId:String
  )
  {
  }
}
@Component({
  selector: 'app-create-loan',
  templateUrl: './create-loan.component.html',
  styleUrls: ['./create-loan.component.css']
})

export class CreateLoanComponent implements OnInit {
  submitted=false
  message:any
  lo:errMessage[]=[]
  public createLoan !: FormGroup;
  constructor(private formBuilder : FormBuilder,private http:HttpClient,
    private service:WebServiceService, private router:Router) { }

  ngOnInit(): void {
    this.submitted=false
    this.createLoan = this.formBuilder.group({
      customerId:['',Validators.pattern('^(?=.{7,7}$)[0-9]+')],
      loanAmount:['',[Validators.min(1000),Validators.max(100000000000),Validators.pattern('^[0-9]*\.[0-9]{2}$')]],
      tradeDate:[''],
      loanStartDate:[''],
      termOfLoanInMonths:['12'],
      paymentFrequency:['1'],
      interestRate:['',[Validators.min(0.01),Validators.max(100)]],
      paymentTerm:['InterestOnly']
    })
  }
  createLoans(){
    this.submitted = true;
    console.log("success")
    console.log(this.createLoan.value)

      this.service.createLoan(this.createLoan.value).subscribe( 
        Response=>{
          console.log(Response)
          this.message=''
          let popup:any = document.getElementById("ConfirmationPopup")
          popup.classList.add("open-popup");
  
      },
      err =>{
        alert(err.error.message || err.error.customerId || "server not connected")
        console.log(err.error.message)
      })
    
  }


}
