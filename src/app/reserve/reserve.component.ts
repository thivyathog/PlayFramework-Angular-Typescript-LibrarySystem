import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, FormArray, Validators} from '@angular/forms';
import {NgbModalConfig, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from "@angular/forms"
import {APIService} from '../api.service';

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.css']
})
export class ReserveComponent implements OnInit {
  constructor(private fb: FormBuilder, private apiService: APIService,) {

  }

  ngOnInit() {
  }

  public onSubmit(): void {


    let borrower = JSON.stringify(this.reserveForm.value);
    console.log("borrower" + borrower);


    this.apiService.reserve(borrower).subscribe((data: any) => {
      document.getElementById("message").innerHTML = data;
      console.log(data + "sucess");

    });
  }

  reserveForm = this.fb.group({

    readerId: ['', [Validators.required]],
    isbn: ['', [Validators.required]],
    pDate: ['', [Validators.required]],


  });
}
