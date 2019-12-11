import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpclientService } from '../service/httpclient.service';

@Component({
  selector: 'app-stockview',
  templateUrl: './stockview.component.html',
  styleUrls: ['./stockview.component.css']
})
export class StockviewComponent implements OnInit {

  isvalid = false;

  stocks: any[];
  stockresponse: any;

  constructor(
    private httpClientService: HttpclientService, private router: Router
  ) { }

  ngOnInit() {
    this.httpClientService.apiData$.subscribe(data => this.stockresponse = data);
    this.isvalid = true;
    this.stocks = this.stockresponse;

    console.log('onServiceView total stock no.' + this.stocks.length);
    // tslint:disable-next-line: prefer-for-of
    // for (var i = 0; i < this.stocks.length; i++) {
    //   console.log('Stock: ' + this.stocks[i].quote);
    //   console.log('Price: ' + this.stocks[i].price);
    // }
  }

}
