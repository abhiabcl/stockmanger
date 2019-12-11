import { Component, OnInit } from '@angular/core';
import { HttpclientService } from '../service/httpclient.service';
import { FormControl, FormGroup, FormArray, FormBuilder } from '@angular/forms';
import { strictEqual } from 'assert';
import { stringify } from '@angular/core/src/util';

@Component({
  selector: 'app-stockmanagement',
  templateUrl: './stockmanagement.component.html',
  styleUrls: ['./stockmanagement.component.css']
})
export class StockmanagementComponent implements OnInit {
   form: FormGroup;
   
  constructor(private httpClientService: HttpclientService, private fb: FormBuilder) { 

    this.form = this.fb.group({
      published: true,
      username: '',
      quotes: this.fb.array([]),
    });
  }

  ngOnInit() {
  }

  onClickSubmit(stock: any) {


    console.log("input data : " + JSON.stringify(stock));
    this.httpClientService.setQuote(JSON.stringify(stock)).subscribe(response => { console.log('Getting response from API: ' + response.body); });
    //http.post('your_url', person).subscribe(status=> console.log(JSON.stringify(status)));
  }

  onSubmit(){
    console.log(this.form.value);
  }

  addStock() {
    
    const creds = this.form.controls.quotes as FormArray;
    creds.push(this.fb.group({
      quotes: '',
    }));
  }
// localhost:8070/api/db-service/rest/db/add
//{ 
// 	"usename":"Abhi", 
// 	"quotes":["GOOG", "ERIC"]
// }
}