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
}
