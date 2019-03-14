import {Component, OnInit} from '@angular/core';
import {APIService} from '../api.service';
import {NavbarService} from '../navbar.service';

@Component({
  selector: 'app-lib-item',
  templateUrl: './lib-item.component.html',
  styleUrls: ['./lib-item.component.css']
})
export class LibItemComponent implements OnInit {

  items: Array<object> = [];
  lists: Array<object> = [];
  searchItems = [];
  title: any
  displayedColumns: string[] = ['isbn', 'type', 'sector', 'title', 'pDate', 'authors', 'noOfpage', 'publisher', 'Available'];
  displayDvd: string[] = ['isbn', 'type', 'sector', 'title', 'pDate', 'lang', 'subs', 'actors', 'producer', 'Available'];
  displaySearch: string[] = ['isbn', 'type', 'title', 'Available'];
  combineArray = [];
  name: string = '';

  constructor(private apiService: APIService, public nav: NavbarService) {
  }

  ngOnInit() {
    this.nav.show();
    this.getLibrary();
    this.getDVD();

    var button: HTMLButtonElement = (<HTMLInputElement>document.getElementById("btn"));
    // button.addEventListener('click',this.getTitle);
    this.combineArray = this.items.concat(this.lists);
  }


  public getTitle(): void {
    this.title = (<HTMLInputElement>document.getElementById("search")).value;
    console.log(this.title + "val");
    if (this.title === "") {
      document.getElementById("searchTable").style.display = "none";

    } else {
      this.apiService.searchTitle(this.title).subscribe((data: any) => {
        this.combineArray = data;

        console.log(data);
      });
      document.getElementById("searchTable").style.display = "block";


      this.title = "";

    }
  }

  public getLibrary(): void {

    this.apiService.getLibrary().subscribe((data: Array<object>) => {
      this.items = data;

      console.log(data);
    });


  }

  public getDVD(): void {

    this.apiService.getDVD().subscribe((data: Array<object>) => {
      this.lists = data;

      console.log(data);
    });

  }


  setValue(): void {

    var $ = (id: any) => document.getElementById(id);

    var optionValue: HTMLInputElement = <HTMLInputElement>$('lol');
    this.name = optionValue.value;
    this.apiService.sendText(this.name).subscribe((response) => {


      console.log(response + "success");
    });

  }


}
