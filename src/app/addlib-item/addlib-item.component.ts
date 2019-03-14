import {Component, OnInit} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {FormGroup, FormControl, FormArray, Validators} from '@angular/forms';
import {Book} from '../book';
import {FormBuilder} from "@angular/forms"
import {dvd} from '../Dvd';
import {APIService} from '../api.service';
import {asTextData, asElementData} from '@angular/core/src/view';

@Component({
  selector: 'app-addlib-item',
  templateUrl: './addlib-item.component.html',
  styleUrls: ['./addlib-item.component.css']
})
export class AddlibItemComponent implements OnInit {

  langs: Array<string> = [];

  constructor(private fb: FormBuilder, private modalService: NgbModal, private apiService: APIService) {
  }

  ngOnInit(): void {
    this.getAvailable();
    this.getSpace();
    var $ = (id: any) => document.getElementById(id);
    //=$(address);
    var optionValue: HTMLSelectElement = <HTMLSelectElement>$('type');


    function shoWform(): void {

      if (optionValue.value == 'Dvd') {
        document.getElementById("frm1").style.display = "none";
        document.getElementById("frm2").style.display = "block";


      } else {
        document.getElementById("frm1").style.display = "block";
        document.getElementById("frm2").style.display = "none";

      }
    }

    optionValue.addEventListener('change', () => {
      shoWform()
    })

    window.onload = function () {

      document.getElementById("frm1").style.display = "block";
      document.getElementById("frm2").style.display = "none";
    };


  }

  public getAvailable(): void {
    this.apiService.getAvailable().subscribe((data: Array<string>) => {
      this.langs = data;

      console.log(data);
    });
  }

  bookForm = this.fb.group({
    isbn: ['', [Validators.required]],
    title: ['', [Validators.required]],
    sector: ['', [Validators.required]],
    pDate: ['', [Validators.required]],

    author1: ['', [Validators.required]],
    author2: [''],

    type: ['book'],
    Publisher: ['', [Validators.required]],
    noOfpages: ['', [Validators.required]],
  });


  get isbn() {
    return this.bookForm.get("isbn").value;
  }

  get title() {
    return this.bookForm.get("title").value;
  }

  get sector() {
    return this.bookForm.get("sector").value;
  }

  get pDate() {
    return this.bookForm.get("pDate").value;
  }

  get author1() {
    return this.bookForm.get("author1").value;
  }

  get author2() {
    return this.bookForm.get("author2").value;
  }

  get Publisher() {
    return this.bookForm.get("Publisher").value;
  }

  get noOfpages() {
    return this.bookForm.get("noOfpages").value;
  }

  get dvdTitle() {
    return this.dvdForm.get("title").value;
  }


  get dvddisbn() {
    return this.dvdForm.get("isbn").value;
  }

  get dvdSubs() {
    return this.dvdForm.get("subz").value;
  }

  get dvdSu() {
    return this.dvdForm.get("sub2").value;
  }

  get dvdProducer() {
    return this.dvdForm.get("producer").value;
  }

  get actor() {
    return this.dvdForm.get("Actors").value;
  }

  get dvdsector() {
    return this.dvdForm.get("sector").value;
  }

  get dvdpDate() {
    return this.dvdForm.get("pDate").value;
  }

  get dvdLangs() {
    return this.dvdForm.get("Lang").value;

  }

  get dvdLangs2() {
    return this.dvdForm.get("aLang2").value;
  }

  public onSubmit(): void {


    let plsWork = JSON.stringify(this.bookForm.value);
    console.log(plsWork);


    this.apiService.sendBook(plsWork).subscribe((data: any) => {
      if (data === "SUCESSFULLY ADDED!") {

        document.getElementById("alert").style.display = "block";
        document.getElementById("alert").style.backgroundColor = "green";
        document.getElementById("message").innerHTML = data;
      } else {
        document.getElementById("alert").style.display = "block";
        document.getElementById("alert").style.backgroundColor = "red";
        document.getElementById("message").innerHTML = data;

      }
      console.log(data + "sucess");


    });
  }

  public dvd(): void {
    let dvdSub = JSON.stringify(this.dvdForm.value);

    console.log(dvdSub);


    this.apiService.sendDVD(dvdSub).subscribe((data: any) => {
      if (data === "SUCESSFULLY ADDED!") {

        document.getElementById("alert").style.display = "block";
        document.getElementById("alert").style.backgroundColor = "green";
        document.getElementById("message").innerHTML = data;
      } else {
        document.getElementById("alert").style.display = "block";
        document.getElementById("alert").style.backgroundColor = "red";
        document.getElementById("message").innerHTML = data;

      }
      console.log(data + "sucess");
     
    });

  }

  dvdForm = this.fb.group({
    isbn: ['', [Validators.required]],
    title: ['', [Validators.required]],
    sector: ['', [Validators.required]],
    pDate: ['', [Validators.required]],
    Lang: ['', [Validators.required]],
    type: ['dvd'],
    aLang2: [''],
    Actors: ['', [Validators.required]],
    producer: ['', [Validators.required]],
    subz: ['', [Validators.required]],
    sub2: [''],
  });


  public getSpace(): void {


    this.apiService.getSpace().subscribe((response) => {
      if (response != 0) {
        document.getElementById("space").innerHTML = "Available Space:" + response;

      } else {
        document.getElementById("space").innerHTML = "Your library is full!:" + response;
        alert("Your library is full!");
      }

      console.log(response + "success");
    });
  }
}
