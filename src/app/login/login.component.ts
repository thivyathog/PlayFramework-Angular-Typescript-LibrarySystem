import {Component, OnInit} from '@angular/core';
import {NavbarService} from '../navbar.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userId: string;
  pass: string;

  constructor(public nav: NavbarService, private router: Router) {
  }

  ngOnInit() {
    this.nav.hide();
  }

  login(): void {
    var $ = (id: any) => document.getElementById(id);

    var usern: HTMLInputElement = <HTMLInputElement>$('uId');
    var passW: HTMLInputElement = <HTMLInputElement>$('passW');
    this.userId = usern.value;
    this.pass = passW.value;
    if (this.userId == "admin" && this.pass == "123") {


      this.router.navigate(["libItem"]);


    } else {
      alert("Invalid credentials")
    }
  }
}
