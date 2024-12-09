import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { BoiteListComponent } from './components/boite-list/boite-list.component';
import { AddBoiteComponent } from './components/boite-add/boite-add.component';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    {path:'listBoites',component:BoiteListComponent},
    { path: 'add', component: AddBoiteComponent }
];
