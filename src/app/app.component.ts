import { Component, OnInit } from '@angular/core';
import { APIService } from './api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ui';

  contacts:Array<object>


  constructor(private apiService: APIService ) {}
  
ngOnInit() {
//this.getContacts();
}

}


