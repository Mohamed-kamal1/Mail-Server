import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpBackend, HttpClient } from '@angular/common/http'
import { ServicesService } from '../services.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private http: HttpClient, private servicesService: ServicesService) { }


  Fname: string = "";
  Lname: string = "";
  email: string = "";
  password: string = "";

  isLogIn: any = "";
  signup: FormGroup | any;

  ngOnInit(): void {
    this.signup = new FormGroup({
      'Fname': new FormControl(),
      'Lname': new FormControl(),
      'email': new FormControl(),
      'password': new FormControl()
    })


  }

  signUpData(signup: FormGroup) {

    this.Fname = String(this.signup.controls.Fname.value);
    this.Lname = String(this.signup.controls.Lname.value);
    this.email = String(this.signup.controls.email.value);
    this.password = String(this.signup.controls.password.value);

    if (this.Fname == 'null' || this.Fname.length == 0) {
      alert("First Name is required");
    }
    else if (this.Lname == 'null' || this.Lname.length == 0) {
      alert("Last Name is required");
    }
    else if (this.email == 'null' || this.email.length == 0) {
      alert("Email is required");
    }
    else if (this.password.length < 8) {
      alert("Password should be at least 8 characters")
    }
    else {
      this.back();
      if (this.isLogIn == "false") {
        alert("this email is already associated with another account");
      }
      else if(this.isLogIn == "true") {
        document.getElementById("done")!.style.display = "block";
        document.getElementById("signUp")!.style.display = "none";
      }
    }
  }

  back() {
    this.servicesService.signUpServices(this.Fname, this.Lname, this.email, this.password)
      .subscribe((response) => {
        this.isLogIn = response.body;
        console.log(this.isLogIn)
      })
  }

}
