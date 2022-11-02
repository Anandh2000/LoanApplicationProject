import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-menu-bar',
  templateUrl: './side-menu-bar.component.html',
  styleUrls: ['./side-menu-bar.component.css']
})
export class SideMenuBarComponent implements OnInit {
  rate =0;
  i=0;
  constructor() { }

  ngOnInit(): void {
    const body:any = document.querySelector('body'),
    sidebar:any = body.querySelector('nav'),
    toggle:any = body.querySelector(".toggle"),
    modeSwitch:any = body.querySelector(".toggle-switch"),
    modeText:any = body.querySelector(".mode-text");

    
    toggle.addEventListener("click", () =>{
      sidebar.classList.toggle("close");
      
  })
  
  modeSwitch.addEventListener("click", () =>{

      body.classList.toggle("dark");
      
      if(body.classList.contains("dark")){
          modeText.innerText = "Light mode";
      }else{
          modeText.innerText = "Dark mode";  
      }
  });

  }

}
