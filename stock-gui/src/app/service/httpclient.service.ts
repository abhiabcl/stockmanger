import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';


export class Stocks {
  constructor(
    public quote: string,
    public price: string
  ) { }
}


@Injectable({
  providedIn: 'root'
})

export class HttpclientService {

  private apiData = new BehaviorSubject<any>(null);
  public apiData$ = this.apiData.asObservable();

  stockresponse: any;

  constructor(
    private httpClient: HttpClient

  ) { }

  getQuote(user: string) {
    return this.httpClient.get<Stocks[]>('/api/stock-service/rest/stock/' + user);
  }

  setData(data) {
    this.apiData.next(data)
  }
}
