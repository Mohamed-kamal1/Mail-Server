import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css','./bootstrap.css']
})
export class AppComponent {
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

    document.getElementById("inbox")!.style.zIndex="-1";
    document.getElementById("starred")!.style.zIndex="-1";
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
