import { Component, OnInit } from '@angular/core';
import { BoiteService } from '../../services/boite.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { Boite } from '../../models/boite/boite.module';

@Component({
  selector: 'app-boite-list',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './boite-list.component.html',
  styleUrls: ['./boite-list.component.css'],
})
export class BoiteListComponent implements OnInit {
  boites: Boite[] = [];
  displayedColumns: string[] = ['id', 'quantite', 'name', 'description', 'pointGeo']; // Colonnes à afficher

  constructor(private boiteService: BoiteService) {}

  ngOnInit(): void {
    this.boiteService.getBoites().subscribe(
      (data) => {
        this.boites = data; // Fill the table with data
      },
      (error) => {
        console.error('Erreur lors de la récupération des données', error);
      }
    );
  }
}
