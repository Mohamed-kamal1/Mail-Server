import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private http: HttpClient, private servicesService: ServicesService) { }

  title = 'Mail-Server';
  select = false;
  signOut_display = false;

  email: FormGroup | any;
  isSent: any = "";
  Recipient: String = "";
  Subject: String = "";
  Content: String = "";


  ngOnInit(): void {
    this.email = new FormGroup({
      'Recipient': new FormControl(),
      'Subject': new FormControl(),
      'Content': new FormControl()
    })
  }

  sendEmail() {
    this.Recipient = String(this.email.controls.Recipient.value);
    this.Subject = String(this.email.controls.Subject.value);
    this.Content = String(this.email.controls.Content.value);

    if (this.Recipient == 'null' || this.Recipient.length == 0) {
      alert("Please specify recipient");
    }
    else {
      this.back();
      if (this.isSent == 'false') {
        alert(`The address "${this.Recipient}" in the "To" field was not recognized. Please make sure that all addresses are properly formed.`);
      }
      else if (this.isSent == 'true') {
        alert("Done");

      }

    }
    // this.loadEmail("inbox", "mohamed", "mohamedkamal");
  }

  back() {
    this.servicesService.sendEmailServices(this.Recipient, this.Subject, this.Content)
      .subscribe((response) => {
        this.isSent = response.body;
        console.log(this.isSent)
      })
  }



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

  signOut() {
    this.signOut_display = !this.signOut_display;
    if (this.signOut_display) {
      document.getElementById("acount")!.style.display = "flex";
    }
    else {
      document.getElementById("acount")!.style.display = "none";
    }

  }



  loadEmail(container:string,username:string,Content:string) {
    let body = document.getElementById(`${container}`)!;

    let email_content = document.createElement("div");
    email_content.className = "email_content";
    email_content.style.height = "50px";
    email_content.style.display = "flex";
    email_content.style.backgroundColor = "#326ce133";
    email_content.style.border = "0";
    email_content.style.borderRadius = "10px";
    email_content.style.padding = "0 10px";
    email_content.style.marginTop = "5px";
    email_content.style.cursor = "pointer";
    email_content.addEventListener("mouseenter", ()=>email_content.style.boxShadow = "0 4px 4px -2px rgb(91, 101, 140)");
    email_content.addEventListener("mouseleave", () => email_content.style.boxShadow = "0 0 0 0");

    let user = document.createElement("p");
    user.className = "user";
    let myText1 = document.createTextNode(`${username}`);
    user.appendChild(myText1);
    user.style.width = "15%";
    user.style.overflow = "hidden";
    user.style.padding = "10px";
    user.style.textOverflow = "ellipsis";

    let email = document.createElement("p");
    email.className = "email";
    let myText2 = document.createTextNode(`${Content}`);
    email.appendChild(myText2);
    email.style.width = "85%";
    email.style.padding = "10px";
    email.style.marginLeft = "50px";
    email.style.overflow = "hidden";
    email.style.textOverflow = "ellipsis";

    email_content.appendChild(user);
    email_content.appendChild(email);
    body.appendChild(email_content);


  }

}
