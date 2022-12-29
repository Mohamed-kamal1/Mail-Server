import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpBackend, HttpClient } from '@angular/common/http'
import { ServicesService } from '../services.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  isSignIn: any = "";
  email: string = "";
  password: string = "";

  constructor(private http: HttpClient, private servicesService: ServicesService, private router: Router) { }
  login: FormGroup | any;

  ngOnInit(): void {
    this.login = new FormGroup({
      'email': new FormControl(),
      'password': new FormControl()
    })
  }

  signInData(login: FormGroup) {
    this.email = String(this.login.controls.email.value);
    this.password = String(this.login.controls.password.value);

    if (this.email == 'null' || this.email.length == 0) {
      alert("Email is required");
    }
    else if (this.password.length < 8) {
      alert("Password should be at least 8 characters")
    }
    else {
      this.back();

      if (this.isSignIn == "false") {
        alert("wrong email or password");
      }
      else if (this.isSignIn == "true") {
        this.router.navigate(['/Home']);
      }
    }
  }

  back() {
    var email = this.login.controls.email.value;
    var password = this.login.controls.password.value;
    this.servicesService.signInServices(email, password)
      .subscribe((response) => {
        var text = response.body
        this.isSignIn = text;
      })
  }

}
