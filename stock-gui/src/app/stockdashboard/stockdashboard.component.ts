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

  constructor(
    private httpClientService: HttpclientService, private router: Router
  ) { }

  ngOnInit() {
  }

  handleSuccessfulResponse(data: any) {


    this.stocks = data.body;
    console.log('on dashboardPage ' + data);
    // tslint:disable-next-line: prefer-for-of
    for (var i = 0; i < this.stocks.length; i++) {
      console.log('Stock: ' + this.stocks[i].quote);
      console.log('Price: ' + this.stocks[i].price);
    }
    this.httpClientService.setData(this.stocks);
    this.router.navigate(['stockview']);
  }

  onClickSubmit(data: any) {
    console.log('Entered user name: ' + data.username);
    if (data.username != null) {
      this.httpClientService.getQuote(data.username).subscribe(
        response => {
          if (response.status == 200)
            this.handleSuccessfulResponse(response)
          else
            alert("Responde from api faile with error code: " + response.status);
        });
    } else {
      alert('user not valid!');
    }
  }
}
