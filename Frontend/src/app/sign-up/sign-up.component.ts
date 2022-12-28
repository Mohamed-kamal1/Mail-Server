import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { HttpBackend, HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private http: HttpClient) { }


  signup: FormGroup | any;

  ngOnInit(): void {
    this.signup = new FormGroup({
      'Fname': new FormControl(),
      'Lname': new FormControl(),
      'email': new FormControl(),
      'password': new FormControl()
    })


  }



  signUpData(login: FormGroup) {
    this.back();
  }

  back() {

    this.http.get('http://localhost:8080/back/signUp', {
      responseType: 'text',
      params: {
        Fname: this.signup.controls.Fname.value,
        Lname: this.signup.controls.Lname.value,
        email: this.signup.controls.email.value,
        password: this.signup.controls.password.value
      },
      observe: "response"

    })
      .subscribe((response) => {
        var text = response.body
      })
  }

}
