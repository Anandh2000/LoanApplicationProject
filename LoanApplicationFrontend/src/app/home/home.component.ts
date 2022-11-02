import { DOCUMENT } from '@angular/common';
import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import {gsap} from 'gsap';
import { ScrollTrigger } from 'gsap/ScrollTrigger';

gsap.registerPlugin(ScrollTrigger);

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
   @ViewChild('secondSection',{static:true}) secondSection!: ElementRef<HTMLDivElement>;
   @ViewChild('thirdSection', { static: true })
  thirdSection!: ElementRef<HTMLDivElement>;

  constructor(@Inject(DOCUMENT) private document:Document) { }

  ngOnInit(): void {
    var t1 = gsap.timeline({
      scrollTrigger:{
        trigger:this.document.querySelector('.container'),
        toggleClass:'active',
        start:'20% center bottom',

      }
    });
    t1.from(".cards",{y:200,opacity:0,duration:1.5})
    

    // gsap.to(this.secondSection.nativeElement,{
    //   scrollTrigger:{
    //     trigger:this.secondSection.nativeElement,
    //     markers:true,
    //     scrub:true,
    //     start:'110% center',
    //   }as gsap.plugins.ScrollTriggerInstanceVars,
    //   scale:1.2,
    //   duration:2,
    //   opacity:0,
    // });
    
    gsap.to(this.document.querySelector('.first'),{
      scrollTrigger:{
        trigger:this.document.querySelector('.first'),
       
        scrub:true,
        start:'50% center',
      },
      opacity:0,
      duration:2,
    });
    gsap.to(this.document.querySelector('.second'),{
      scrollTrigger:{
        trigger:this.document.querySelector('.second'),
       
        scrub:true,
        start:'50% center',
      },
      opacity:0,
      duration:2,
    });
    gsap.to(this.document.querySelector('.third'),{
      scrollTrigger:{
        trigger:this.document.querySelector('.third'),
       
        scrub:true,
        start:'50% center',
      },
      opacity:0,
      duration:2,
    });
  }

 

}
