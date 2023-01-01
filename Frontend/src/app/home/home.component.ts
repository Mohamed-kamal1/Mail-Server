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
        this.click("");

      }

    }

  }

  back() {
    this.servicesService.sendEmailServices(this.Recipient, this.Subject, this.Content)
      .subscribe((response) => {
        this.isSent = response.body;
        console.log(this.isSent)
      })
  }

  folder_back(folder: string) {
    this.click(`${folder}`);
    document.getElementById(`${folder}`)!.innerHTML = "";
    this.servicesService.openFolderServices(folder)
      .subscribe((response) => {
        console.log(response.body)
      })

    for (let i = 0; i < 10; i++)
      this.loadEmail(`${folder}`, "mohamedkamalmohamed", "subject", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tincidunt volutpat nibh eu elementum. ", true);
  }
  add_to(folder: string, Recipient: any, Subject: any, Content: any) {
    this.servicesService.addTo(folder, Recipient, Subject, Content)
      .subscribe((Response) => {
        console.log(Response.body);
      });
  }
  remove_from(folder: string, Recipient: any, Subject: any, Content: any) {
    this.servicesService.removeFrom(folder, Recipient, Subject, Content)
      .subscribe((Response) => {
        console.log(Response.body);
      });
  }
  delete_forever(Recipient: any, Subject: any, Content: any) {
    this.servicesService.delete_forever(Recipient, Subject, Content)
      .subscribe((Response) => {
        console.log(Response.body);
      });
  }
  contact() {
    this.click("contacts");
    this.servicesService.loadContacts()
      .subscribe((Response) => {
        console.log(Response.body);
      });
    for (let i = 0; i < 10; i++)
      this.loadContact("mohamed", "kamal", "gfhffg");

  }
  add_contact(Fname: string, Lname: string, email: string) {
    this.servicesService.addContect(Fname, Lname, email)
      .subscribe((Response) => {
        console.log(Response.body);
      });
  }
  delete_contact(Fname: string, Lname: string, email: string) {
    this.servicesService.deleteContect(Fname, Lname, email)
      .subscribe((Response) => {
        console.log(Response.body);
      });
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
    document.getElementById("contacts")!.style.zIndex = "-1";
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
    else if (click == "contacts") {
      document.getElementById("contacts")!.style.zIndex = "1";
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

  loadEmail(container: string, username: string, subject: string, Content: string, starred: boolean) {
    let starredF = false;


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
    email_content.addEventListener("mouseenter", () => email_content.style.boxShadow = "0 4px 4px -2px rgb(91, 101, 140)");
    email_content.addEventListener("mouseleave", () => email_content.style.boxShadow = "0 0 0 0");


    let checkbox = document.createElement("input");
    checkbox.setAttribute("type", "checkbox");

    let star = document.createElement("span");
    star.className = "material-symbols-outlined";
    star.appendChild(document.createTextNode("star"));
    star.style.padding = "10px";
    if (container == "starred" || starred) {
      star.style.color = "yellow";
      starredF = true;
    }
    star.addEventListener("click", () => {
      if (starredF) {
        star.style.color = "black";
        starredF = !starredF;
        this.remove_from("starred", username, subject, Content);

      }
      else {
        star.style.color = "yellow";
        starredF = !starredF;
        this.add_to("starred", username, subject, Content);
      }

    });

    let user = document.createElement("p");
    user.className = "user";
    let myText1 = document.createTextNode(`${username}`);
    user.appendChild(myText1);
    user.style.maxWidth = "15%";
    user.style.overflow = "hidden";
    user.style.padding = "10px";
    user.style.textOverflow = "ellipsis";

    let email = document.createElement("p");
    email.className = "email";
    let myText2 = document.createTextNode(`${Content}`);
    email.appendChild(myText2);
    email.style.maxWidth = "70%";
    email.style.overflow = "hidden";
    email.style.padding = "10px";
    email.style.textOverflow = "ellipsis";
    /////////
    email.addEventListener("click", () => {
      let message = document.createElement("div");
      message.style.width = "80%";
      message.style.height = "90%";
      message.style.backgroundColor = "white";
      message.style.zIndex = "100";
      message.style.position = "absolute";
      message.style.top = "5%";
      message.style.left = "10%";
      message.style.borderRadius = "20px";
      message.style.boxShadow = "0 4px 4px -2px rgb(91, 101, 140)";
      let header = document.createElement("div");
      message.appendChild(header);
      let Subject = document.createElement("p");
      let close = document.createElement("span");
      let person = document.createElement("div");
      let contant = document.createElement("p");
      header.appendChild(Subject);
      header.appendChild(close);
      message.appendChild(person);
      message.appendChild(contant);
      header.style.width = "100%";
      header.style.height = "20%";
      header.style.display = "flex";
      header.style.padding = "20px";

      close.className = "material-symbols-outlined";
      close.appendChild(document.createTextNode("close"));
      close.style.marginLeft = "5%";
      close.style.cursor = "pointer";
      close.addEventListener("click", () => message.style.display = "none");

      Subject.appendChild(document.createTextNode(`${subject}`));
      Subject.style.width = "90%";
      Subject.style.fontWeight = "bold";
      Subject.style.fontSize = "20px";

      person.style.height = "10%";
      person.style.width = "100%";
      person.style.padding = "10px 20px";
      person.style.display = "flex";
      let icon = document.createElement("span");
      let name = document.createElement("p");
      person.appendChild(icon);
      person.appendChild(name);

      icon.className = "material-symbols-outlined";
      icon.appendChild(document.createTextNode("person"));
      icon.style.marginRight = "20px";
      name.appendChild(document.createTextNode(`${username}`));

      contant.style.height = "70%";
      contant.style.width = "100%";
      contant.appendChild(document.createTextNode(`${Content}`));
      contant.style.padding = "20px 20px";

      body.appendChild(message);

    });


    let trash = document.createElement("span");
    trash.className = "material-symbols-outlined";

    trash.style.padding = "10px";
    trash.style.marginLeft = "10px";
    trash.addEventListener("click", () => {
      email_content.style.display = "none";
      if (container == "trash") this.remove_from("trash", user, subject, Content);
      else this.add_to("trash", user, subject, Content);
    });


    email_content.appendChild(checkbox);
    if (container != "trash") {
      email_content.appendChild(star);
    }
    email_content.appendChild(user);
    email_content.appendChild(email);
    email_content.appendChild(trash);
    body.appendChild(email_content);

    if (container != "trash") {
      trash.appendChild(document.createTextNode("delete"));
    }
    else {
      trash.appendChild(document.createTextNode("restore_from_trash"));
      let delete_forever = document.createElement("span");
      delete_forever.className = "material-symbols-outlined";
      delete_forever.appendChild(document.createTextNode("delete_forever"));
      delete_forever.style.padding = "10px";
      delete_forever.style.marginLeft = "10px";
      delete_forever.addEventListener("click", () => {
        email_content.style.display = "none";
        this.delete_forever(user, subject, Content);

      })
      email_content.appendChild(delete_forever);
    }
  }

  loadContact(Fname: string, Lname: string, email: string) {
    let click = false;
    let contacts = document.getElementById("contacts_body")!;
    let contant_body = document.createElement("div");
    let body = document.createElement("div");
    body.appendChild(contant_body);
    let icon = document.createElement("span");
    icon.className = "material-symbols-outlined";
    icon.appendChild(document.createTextNode("account_circle"));
    let remove = document.createElement("span");
    remove.className = "material-symbols-outlined";
    remove.appendChild(document.createTextNode("delete"));
    remove.style.display = "none";
    remove.style.marginLeft = "20px";
    remove.addEventListener("click", () => {
      this.delete_contact(Fname, Lname, email);
      body.style.display = "none";
    })

    icon.style.marginRight = "10px";
    icon.style.color = "rgb(53 101 216)";
    let name = document.createElement("p");
    name.appendChild(document.createTextNode(`${Fname} ${Lname}`));
    name.style.fontSize = "20px";
    name.style.marginTop = "10px";
    contant_body.style.display = "flex";
    contant_body.style.alignItems = "center";
    body.style.margin = "0";
    body.style.marginBottom = "2px";
    body.style.padding = "0";
    body.style.paddingLeft = "10px";
    body.style.borderRadius = "30px";
    body.style.cursor = "pointer";

    contant_body.addEventListener("mouseenter", () => {
      if (!click) {
        body.style.boxShadow = "0 4px 4px -2px rgb(91, 101, 140)";
        body.style.backgroundColor = "aliceblue";
      }
    });
    contant_body.addEventListener("mouseleave", () => {
      if (!click) {
        body.style.boxShadow = "0 0 0 0";
        body.style.backgroundColor = "white";
      }
    });

    let mail = document.createElement("div");
    let text = document.createTextNode(`${email}`);
    mail.appendChild(text);
    mail.style.display = "none";
    mail.style.padding = "0 0 10px 40px"
    body.appendChild(mail);
    contant_body.addEventListener("click", () => {


      if (!click) {
        body.style.backgroundColor = "rgb(186, 200, 233)";
        mail.style.display = "block";
        remove.style.display = "block";

      }
      else {
        body.style.backgroundColor = "white";
        mail.style.display = "none";
        remove.style.display = "none";

      }
      click = !click;

    });


    contant_body.appendChild(icon);
    contant_body.appendChild(name);
    contant_body.appendChild(remove);

    contacts.appendChild(body);

  }

}
