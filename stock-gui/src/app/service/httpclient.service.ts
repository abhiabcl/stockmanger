import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';


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
    return this.httpClient.get<any[]>('/api/stock-service/rest/stock/' + user, { observe: 'response' });
  }

  setQuote(postdata: any) {

    let httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.httpClient.post<any>('/api/db-service/rest/db/', postdata, {
      headers: httpHeaders,
      observe: 'response'
    });
  }

  setData(data) {
    this.apiData.next(data)
  }
}
