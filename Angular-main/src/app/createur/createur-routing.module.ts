import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateurComponent } from './createur.component';
import { CreateurdashboardComponent } from './pages/createurdashboard/createurdashboard.component';
import { CreatPojectComponent } from './pages/creat-poject/creat-poject.component';
import { AllProjectsComponent } from './pages/all-projects/all-projects.component';
import { UpdateProjectComponent } from './pages/update-project/update-project.component';

const routes: Routes = [
  { path: '', component: CreateurComponent },
  { path: 'dashboard', component: CreateurdashboardComponent },
  { path: 'project', component: CreatPojectComponent },
  { path: 'projects', component: AllProjectsComponent },
  { path: 'update/:id', component: UpdateProjectComponent }




];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateurRoutingModule { }
