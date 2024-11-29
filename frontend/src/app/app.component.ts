import { Component } from '@angular/core';
import { BoiteListComponent } from './components/boite-list/boite-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [BoiteListComponent],
  templateUrl: `./app.component.html`,
  styleUrls:['./app.component.css']
})
export class AppComponent {
  title='frontend'
}
