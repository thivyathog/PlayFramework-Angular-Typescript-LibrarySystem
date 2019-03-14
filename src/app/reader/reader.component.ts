import {Component, OnInit} from '@angular/core';
import {Validators} from '@angular/forms';

import {FormBuilder} from "@angular/forms"
  ;
import {APIService} from '../api.service';

@Component({
  selector: 'app-reader',
  templateUrl: './reader.component.html',
  styleUrls: ['./reader.component.css']
})
export class ReaderComponent implements OnInit {
  readerList: Array<object> = [];
  displayedColumns: string[] = ['readerId', 'name', 'contact', 'email'];

  constructor(private fb: FormBuilder, private apiService: APIService) {
  }

  ngOnInit() {
    this.getReader();

  }


  onSubmit(): void {


    let reader = JSON.stringify(this.readerForm.value);
    console.log(reader);


    this.apiService.sendReader(reader).subscribe((data: any) => {

      document.getElementById("alert").style.display = "block";
      document.getElementById("alert").style.backgroundColor = "green";
      document.getElementById("message").innerHTML = data;
 
      console.log(data + "sucess");
 

    });
 

  
   
  }

  readerForm = this.fb.group({

    readerId: ['', [Validators.required]],
    name: ['', [Validators.required]],
    email: ['', [Validators.required]],

    contactNo: ['', [Validators.required]],

  });

  public getReader(): void {

    this.apiService.getReader().subscribe((data: Array<object>) => {
      this.readerList = data;

      console.log(data);
    });
  }
}
