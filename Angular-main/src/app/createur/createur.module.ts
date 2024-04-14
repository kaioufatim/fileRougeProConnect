import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateurRoutingModule } from './createur-routing.module';
import { CreateurComponent } from './createur.component';
import { CreateurdashboardComponent } from './pages/createurdashboard/createurdashboard.component';
import { CreatPojectComponent } from './pages/creat-poject/creat-poject.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgZorroAntdModule } from '../NgZorroAntModule';
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';



@NgModule({
  declarations: [
    CreateurComponent,
    CreateurdashboardComponent,
    CreatPojectComponent,
    AllProjectsComponent,
    UpdateProjectComponent,
    
  ],
  imports: [
    CommonModule,
    CreateurRoutingModule,
    ReactiveFormsModule,
    NgZorroAntdModule,

  ]
})
export class CreateurModule { }
