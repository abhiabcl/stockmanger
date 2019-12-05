import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpclientService } from '../service/httpclient.service';


@Component({
  selector: 'app-stockdashboard',
  templateUrl: './stockdashboard.component.html',
  styleUrls: ['./stockdashboard.component.css']
})
export class StockdashboardComponent implements OnInit {
  stocks: any[];
  stockresponse: string;

  constructor(
    private httpClientService: HttpclientService, private router: Router
  ) { }

  ngOnInit() {
  }

  handleSuccessfulResponse(data: any) {
    this.httpClientService.setData(data);

    this.stocks = data;
    console.log('on dashboardPage ' + this.stockresponse);
    // tslint:disable-next-line: prefer-for-of
    for (var i = 0; i < this.stocks.length; i++) {
      console.log('Stock: ' + this.stocks[i].quote);
      console.log('Price: ' + this.stocks[i].price);
    }

    this.router.navigate(['stockview']);
  }

  onClickSubmit(data: any) {
    // alert('Entered user name: ' + data.username);
    if (data.username != null) {
      this.httpClientService.getQuote(data.username).subscribe(
        response => this.handleSuccessfulResponse(response),
      );
    } else {
      alert('user not valid!');
    }
  }
}
