
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WebServiceService } from '../service/web-service.service';

export class Schedule{
  constructor(
    public paymentDate:Date,
    public principal:number,
    public projectedInterest:number,
    public paymentStatus:String,
    public paymentAmount:number
  )
  {
  }
}

@Component({
  selector: 'app-payment-schedule',
  templateUrl: './payment-schedule.component.html',
  styleUrls: ['./payment-schedule.component.css']
})
export class PaymentScheduleComponent implements OnInit {
  chart:any=[];
  id:number=0
  page:number=1;
  count:number=0;
  tableSize:number=10;
  tableSizes:any=[5,10,15,20]
  schedules:any =[]
  constructor(private service:WebServiceService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.scheduleList()
  }
  scheduleList(){
    this.id=this.route.snapshot.params['id'];
    console.log("sdsd")
    this.service.scheduleHistory(this.id).subscribe(
      res => {
        console.log(res)
        this.schedules = res.paymentSchedules
    }
     )
  }

  payment(){
    this.id=this.route.snapshot.params['id'];
    this.service.payment(this.id).subscribe(
      (Response) => {
        console.log(Response)
        this.schedules = Response
        let popup:any = document.getElementById("ConfirmationPopup")
        popup.classList.remove("open-popup");
        }

     )
  }
  back(){
    let popup:any = document.getElementById("ConfirmationPopup")
    popup.classList.remove("open-popup");
  }

  onConfirm(){
    let popup:any = document.getElementById("ConfirmationPopup")
    popup.classList.add("open-popup");
  }


  onTableDataChange(event:any){
    this.page=event;
    this.scheduleList();
  }

  onTableSizeChange(event:any):void{
    this.tableSize=event.target.value;
    this.page=1;
    this.scheduleList();
  }

}
