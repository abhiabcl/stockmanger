import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StockviewComponent } from './stockview/stockview.component';
import { StockmanagementComponent } from './stockmanagement/stockmanagement.component';
import { StockdashboardComponent } from './stockdashboard/stockdashboard.component';

const routes: Routes = [
  { path: 'stockview', component: StockviewComponent },
  { path: 'stockmange', component: StockmanagementComponent },
  { path: 'stockdashboard', component: StockdashboardComponent }
];

@NgModule({
   imports: [RouterModule.forRoot(routes)],
   exports: [RouterModule]
})

export class AppRoutingModule { }
