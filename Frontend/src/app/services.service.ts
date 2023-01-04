import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {

  constructor(private http: HttpClient) { }

  signUpServices(fname: any, lname: any, Email: any, Password: any) {
    return this.http.get(`http://localhost:8080/back/signUp`, {
      responseType: 'text',
      params: {
        name: fname + " " + lname,
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
  addContect(name: any, Emails: any) {
    return this.http.get('http://localhost:8080/back/addcontact', {
      responseType: 'text',
      params: {
        name: name,
        emails: Emails,
      },
      observe: "response"
    })
  }
  deleteContect(fname: any, lname: any, Email: any) {
    return this.http.get('http://localhost:8080/back/deletecontact', {
      responseType: 'text',
      params: {
        Fname: fname,
        Lname: lname,
        email: Email,
      },
      observe: "response"
    })
  }
  sendEmailServices(Recipient: any, Subject: any, Content: any,date:string) {
    let attachments: any=[] ;
    return this.http.get(`http://localhost:8080/back/email`, {
      responseType: 'text',
      params: {
        Subject: Subject,
        Recipient: Recipient,
        date: date,
        Content: Content,
       // attachments: attachments
      },
      observe: "response"
    })
  }
  openFolderServices(folder: string,sortby:string) {
    return this.http.get(`http://localhost:8080/back/openfolder`, {
      responseType: 'json',
      params: {
        folder:folder,
        sortby: sortby
      },
      observe: "response"
    })
  }
  loadContacts() {
    return this.http.get(`http://localhost:8080/back/contacts`, {
      responseType: 'json',
      params: {
      },
      observe: "response"
    })
  }

  add_remove(folder:string,EmailID: string[]) {
    return this.http.get(`http://localhost:8080/back/addRemove`, {
      responseType: 'text',
      params: {
        folder:folder,
        sID: EmailID[0],
        sIndex: EmailID[1],
        rID: EmailID[2],
        rIndex: EmailID[3],
      },
      observe: "response"
    })
  }

  addToDraft(Recipient: any, Subject: any, Content: any, date:string) {
      let attachments: any = [];
      return this.http.get(`http://localhost:8080/back/todraft`, {
        responseType: 'text',
        params: {
          Subject: Subject,
          Recipient: Recipient,
          date: date,
          Content: Content,
          // attachments: attachments
        },
        observe: "response"
      })
  }
  delete_forever(Recipient: any, EmailID:string[]) {
    return this.http.get(`http://localhost:8080/back/deleteforever`, {
      responseType: 'text',
      params: {
        sID: EmailID[0],
        sIndex: EmailID[1],
        rID: EmailID[2],
        rIndex: EmailID[3],
      },
      observe: "response"
    })
  }

}
