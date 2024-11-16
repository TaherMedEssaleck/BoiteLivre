import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component'; // Make sure this import is correct
import { BoiteComponent } from './components/boite/boite.component';
import { BoiteDetailComponent } from './components/boite-detail/boite-detail.component';
import { BoiteService } from './services/boite.service';
import { appRoutes } from './app.routes';

@NgModule({
  declarations: [
    BoiteComponent,
    BoiteDetailComponent,
    // Remove AppComponent from here
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    AppComponent, // Import the standalone component here
  ],
  providers: [BoiteService],
  // bootstrap: [AppComponent]
})
export class AppModule {}
