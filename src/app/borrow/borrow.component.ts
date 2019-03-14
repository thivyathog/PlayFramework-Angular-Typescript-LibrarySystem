import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, FormArray, Validators} from '@angular/forms';
import {NgbModalConfig, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from "@angular/forms"

import {APIService} from '../api.service';

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.component.html',
  styleUrls: ['./borrow.component.css']
})
export class BorrowComponent implements OnInit {
  lists: Array<object> = [];
  report: Array<object> = [];

  constructor(private fb: FormBuilder, private apiService: APIService, config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  open(content) {
    this.modalService.open(content);
  }

  ngOnInit() {
    this.getReport();

  }

  public onSubmit(): void {


    let borrower = JSON.stringify(this.borrowForm.value);
    console.log("borrower" + borrower);


    this.apiService.borrower(borrower).subscribe((data: any) => {
      if (data === "You have successfully borrowed the library item!") {
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

  borrowForm = this.fb.group({

    readerId: ['', [Validators.required]],
    isbn: ['', [Validators.required]],
    pDate: ['', [Validators.required]],


  });

  public getReport(): void {

    this.apiService.getBorrowerDetails().subscribe((data: Array<object>) => {
      this.report = data;

      console.log(data);
    });


  }
}
