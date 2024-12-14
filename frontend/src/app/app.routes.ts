import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { BoiteListComponent } from './components/boite-list/boite-list.component';
import { AddBoiteComponent } from './components/boite-add/boite-add.component';
import { BoiteEditComponent } from './components/boite-edit/boite-edit.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'add', component: AddBoiteComponent, canActivate: [AuthGuard] },
    { path: 'listBoites', component: BoiteListComponent, canActivate: [AuthGuard] },
    { path: 'editBoite/:id', component: BoiteEditComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: '/login', pathMatch: 'full' }, 
    { path: '**', redirectTo: '/login' },
];
