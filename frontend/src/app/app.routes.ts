import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { BoiteListComponent } from './components/boite-list/boite-list.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { 
      path: 'listBoites', 
      component: BoiteListComponent, 
      canActivate: [AuthGuard] // Ajout du guard ici
    },
    { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirection par défaut
    { path: '**', redirectTo: '/login' } // Page 404 ou route inconnue
];
