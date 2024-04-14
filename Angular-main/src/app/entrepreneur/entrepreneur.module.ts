import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EntrepreneurRoutingModule } from './entrepreneur-routing.module';
import { EntrepreneurComponent } from './entrepreneur.component';
import { EntrepreneurDashboardComponent } from './pages/entrepreneur-dashboard/entrepreneur-dashboard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgZorroAntdModule } from '../NgZorroAntModule';
import { ProjectDetailsComponent } from './pages/project-details/project-details.component';
import { MyPrepositionsComponent } from './pages/my-prepositions/my-prepositions.component';
import { ReviewComponent } from './pages/review/review.component';


@NgModule({
  declarations: [
    EntrepreneurComponent,
    EntrepreneurDashboardComponent,
    ProjectDetailsComponent,
    MyPrepositionsComponent,
    ReviewComponent
  ],
  imports: [
    CommonModule,
    EntrepreneurRoutingModule,
    ReactiveFormsModule,
    NgZorroAntdModule,
    FormsModule,
  ]
})
export class EntrepreneurModule { }
