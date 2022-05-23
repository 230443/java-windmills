import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HelpComponent} from "./help/help.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent},
  { path: 'help', component: HelpComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
