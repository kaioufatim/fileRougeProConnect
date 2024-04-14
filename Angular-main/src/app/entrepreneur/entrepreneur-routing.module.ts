import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntrepreneurComponent } from './entrepreneur.component';
import { EntrepreneurDashboardComponent } from './pages/entrepreneur-dashboard/entrepreneur-dashboard.component';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { MyPrepositionsComponent } from './pages/my-prepositions/my-prepositions.component';
import { ReviewComponent } from './pages/review/review.component';

const routes: Routes = [
  { path: '', component: EntrepreneurComponent },
  { path: 'dashboard', component: EntrepreneurDashboardComponent },
  { path: 'project/:projectId', component: ProjectDetailsComponent },
  { path: 'propositions', component: MyPrepositionsComponent },
  { path: 'review/:Id', component: ReviewComponent },



];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
}) 
export class EntrepreneurRoutingModule { }
