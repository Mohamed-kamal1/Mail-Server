import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  title = 'Mail-Server';

  select = false;



  select_menu() {
    this.select = !this.select;
    if (this.select) {
      document.getElementById("select")!.style.display = "block";
    }
    else {
      document.getElementById("select")!.style.display = "none";

    }
  }
  click(click: string) {

    document.getElementById("inbox")!.style.zIndex = "-1";
    document.getElementById("starred")!.style.zIndex = "-1";
    document.getElementById("snoozed")!.style.zIndex = "-1";
    document.getElementById("sent")!.style.zIndex = "-1";
    document.getElementById("draft")!.style.zIndex = "-1";
    document.getElementById("trash")!.style.zIndex = "-1";
    document.getElementById("person")!.style.zIndex = "-1";
    document.getElementById("lable")!.style.zIndex = "-1";
    document.getElementById("compose")!.style.zIndex = "-1";
    if (click == "inbox") {
      document.getElementById("inbox")!.style.zIndex = "1";
    }
    else if (click == "starred") {
      document.getElementById("starred")!.style.zIndex = "1";
    }
    else if (click == "snoozed") {
      document.getElementById("snoozed")!.style.zIndex = "1";
    }
    else if (click == "sent") {
      document.getElementById("sent")!.style.zIndex = "1";
    }
    else if (click == "draft") {
      document.getElementById("draft")!.style.zIndex = "1";
    }
    else if (click == "trash") {
      document.getElementById("trash")!.style.zIndex = "1";
    }
    else if (click == "person") {
      document.getElementById("person")!.style.zIndex = "1";
    }
    else if (click == "lable") {
      document.getElementById("lable")!.style.zIndex = "1";
    }
    else if (click == "compose") {
      document.getElementById("compose")!.style.zIndex = "1";
    }
  }


  
}
