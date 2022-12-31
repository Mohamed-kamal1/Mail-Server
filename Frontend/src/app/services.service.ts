import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private http: HttpClient) { }

  signUpServices(fname: any, lname: any, Email: any, Password: any) {
    return this.http.get('http://localhost:8080/back/signUp', {
      responseType: 'text',
      params: {
        Fname: fname,
        Lname: lname,
        email: Email,
        password: Password
      },
      observe: "response"
    })
  }

  signInServices(Email: any, Password: any) {
    return this.http.get('http://localhost:8080/back/login', {
      responseType: 'text',
      params: {
        email: Email,
        password: Password
      },
      observe: "response"
    })
  }

  sendEmailServices(Recipient: any, Subject: any, Content: any) {
    return this.http.get('http://localhost:8080/back/email', {
      responseType: 'text',
      params: {
        Recipient: Recipient,
        Subject: Subject,
        Content: Content
      },
      observe: "response"
    })
  }
  openFolderServices(folder: string) {
    return this.http.get(`http://localhost:8080/back/${folder}`, {
      responseType: 'json',
      params: {
      },
      observe: "response"
    })
  }
  addTo(folder: string, Recipient: any, Subject: any, Content: any) {
    return this.http.get(`http://localhost:8080/back/to${folder}`, {
      responseType: 'text',
      params: {
        Recipient: Recipient,
        Subject: Subject,
        Content: Content
      },
      observe: "response"
    })
  }
  removeFrom(folder: string, Recipient: any, Subject: any, Content: any) {
    return this.http.get(`http://localhost:8080/back/from${folder}`, {
      responseType: 'text',
      params: {
        Recipient: Recipient,
        Subject: Subject,
        Content: Content
      },
      observe: "response"
    })
  }
  delete_forever(Recipient: any, Subject: any, Content: any) {
    return this.http.get(`http://localhost:8080/back/deleteforever`, {
      responseType: 'text',
      params: {
        Recipient: Recipient,
        Subject: Subject,
        Content: Content
      },
      observe: "response"
    })
  }
}
