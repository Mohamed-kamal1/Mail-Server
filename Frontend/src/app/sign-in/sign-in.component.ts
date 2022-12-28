import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  login: FormGroup|any;
  constructor() { }

  ngOnInit(): void {
    this.login = new FormGroup({
      'email': new FormControl(),
      'password':new FormControl()
    })
  }

  signInData(login:FormGroup) {

    console.log(this.login.value);
  }

}
