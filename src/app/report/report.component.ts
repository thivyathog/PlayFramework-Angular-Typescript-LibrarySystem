import {Component, OnInit} from '@angular/core';

import {APIService} from '../api.service';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  reports: Array<object> = [];
  total;

  displayedColumns: string[] = ['isbn', 'Title', 'bDate', 'Available', 'Due Cost'];

  constructor(private apiService: APIService) {
  }

  ngOnInit() {
    this.getReport();

  }

  public getReport(): void {

    this.apiService.getBorrowerDetails().subscribe((data: Array<object>) => {
      this.reports = data;
      let tempTotal: number = 0;
      data.forEach((e: any) => {
        tempTotal = tempTotal + Number(e.dueCost);
        console.log(e.dueCost);
      });
      this.total = tempTotal;
      console.log(data);
    });
  }


}
