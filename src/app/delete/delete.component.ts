import {Component, OnInit} from '@angular/core';
import {APIService} from '../api.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  constructor(private apiService: APIService) {
  }

  ngOnInit() {
  }


  name: string = '';

  setValue(): void {

    var $ = (id: any) => document.getElementById(id);

    var optionValue: HTMLInputElement = <HTMLInputElement>$('id');
    this.name = optionValue.value;
    this.apiService.sendText(this.name).subscribe((response) => {

      document.getElementById("type").innerHTML = "The Deleted item is a " + response;
      console.log(response + "success");



    });
  }

}
