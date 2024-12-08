import { Component } from '@angular/core';
import { BoiteListComponent } from './components/boite-list/boite-list.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [BoiteListComponent, RouterOutlet],
  templateUrl: `./app.component.html`,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend'
}
