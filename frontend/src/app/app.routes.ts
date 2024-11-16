import { Routes } from '@angular/router';
import { BoiteComponent } from './components/boite/boite.component';
import { BoiteDetailComponent } from './components/boite-detail/boite-detail.component';

export const appRoutes: Routes = [
  { path: 'boites', component: BoiteComponent },
  { path: 'boites/create', component: BoiteDetailComponent },
  { path: 'boites/:id', component: BoiteDetailComponent },
  { path: '', redirectTo: '/boites', pathMatch: 'full' }
];
