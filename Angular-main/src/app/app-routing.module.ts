import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './basic/components/signup/signup.component';
import { SignupCreateurComponent } from './basic/components/signup-createur/signup-createur.component';
import { SignupEntrepreneurComponent } from './basic/components/signup-entrepreneur/signup-entrepreneur.component';
import { LoginComponent } from './basic/components/login/login.component';
import { MyPrepositionsComponent } from './entrepreneur/pages/my-prepositions/my-prepositions.component';

const routes: Routes = [
  { path:'register_createur',component:SignupCreateurComponent},
  { path:'register_entrepreneur',component:SignupEntrepreneurComponent},
  { path:'login',component:LoginComponent},
  { path:'register',component:SignupComponent},
  { path: 'entrepreneur', loadChildren: () => import('./entrepreneur/entrepreneur.module').then(m => m.EntrepreneurModule) }, 
  { path: 'createur', loadChildren: () => import('./createur/createur.module').then(m => m.CreateurModule) },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
