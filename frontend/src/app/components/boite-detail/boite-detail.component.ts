import { Component, OnInit } from "@angular/core";
import { BoiteService } from "../../services/boite.service";
import { Router } from "@angular/router";
import { Boite } from "../../models/boite.model";
import { FormsModule } from '@angular/forms';  // Importer FormsModule

@Component({
  selector: 'app-boite-detail',
  standalone: true,
  imports: [FormsModule],  // Ajouter FormsModule dans imports
  templateUrl: './boite-detail.component.html',
  styleUrls: ['./boite-detail.component.css']
})
export class BoiteDetailComponent implements OnInit {
  boite: Boite = { id: null, name: '', description: '' };

  constructor(private boiteService: BoiteService, private router: Router) {}

  ngOnInit(): void {
    // Initialize the boite or fetch details based on the route
  }

  saveBoite(): void {
    if (this.boite.id === null) {
      // If id is null, create a new boite
      this.boiteService.createBoite(this.boite).subscribe(() => {
        this.router.navigate(['/boites']);
      });
    } else {
      // Otherwise, update the existing boite
      this.boiteService.updateBoite(this.boite).subscribe(() => {
        this.router.navigate(['/boites']);
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/boites']);  // Navigate back to the list of boites
  }
}
