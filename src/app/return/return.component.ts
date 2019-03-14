import {Component, OnInit} from '@angular/core';
import {APIService} from '../api.service';
import {FormGroup, FormControl, FormArray, Validators} from '@angular/forms';

import {FormBuilder} from "@angular/forms"


@Component({
  selector: 'app-return',
  templateUrl: './return.component.html',
  styleUrls: ['./return.component.css']
})


export class ReturnComponent implements OnInit {

  name: string = '';

  constructor(private apiService: APIService, private fb: FormBuilder) {
  }

  ngOnInit() {


  }

  onSubmit(): void {


    let returnItem = JSON.stringify(this.returnForm.value);
    console.log("returnItem" + returnItem);


    this.apiService.return(returnItem).subscribe((data: any) => {
      if(data!=0){
      document.getElementById('cost').innerHTML = "FEE TO PAY DUE TO DELAY:$" + data;
      console.log(data + "sucess");
      }else{
        document.getElementById('cost').innerHTML = "LIBRARY ITEM WITH THIS ISBN ISNT AVAILABLE " ;
      }
    });
  }

  returnForm = this.fb.group({


    isbn: ['', [Validators.required]],
    pDate: ['', [Validators.required]],


  });


  setValue(): void {

    var $ = (id: any) => document.getElementById(id);

    var optionValue: HTMLInputElement = <HTMLInputElement>$('lol');
    this.name = optionValue.value;
    this.apiService.sendText(this.name).subscribe((response) => {


      console.log(response + "success");
    });
  }


}
