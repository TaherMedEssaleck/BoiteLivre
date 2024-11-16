import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { BoiteComponent } from './components/boite/boite.component';
import { BoiteDetailComponent } from './components/boite-detail/boite-detail.component';
import { BoiteService } from './services/boite.service';
import { appRoutes } from './app.routes';

@NgModule({
  declarations: [
    AppComponent,
    BoiteComponent,
    BoiteDetailComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [BoiteService],
  bootstrap: [AppComponent]
})
export class AppModule {}
