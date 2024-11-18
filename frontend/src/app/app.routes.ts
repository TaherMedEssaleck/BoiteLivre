import { Routes } from '@angular/router';
import { BoiteComponent } from './components/boite/boite.component';
import { BoiteDetailComponent } from './components/boite-detail/boite-detail.component';
import { LoginComponent } from './components/login/login.component'; 
import { SignupComponent } from './components/signup/signup.component'; 


export const appRoutes: Routes = [
  { path: 'boites', component: BoiteComponent },
  { path: 'boites/create', component: BoiteDetailComponent },
  { path: 'boites/:id', component: BoiteDetailComponent },
  { path: 'login', component: LoginComponent }, 
  { path: 'signup', component: SignupComponent }, 
  { path: '', redirectTo: '/login', pathMatch: 'full' } 
];
