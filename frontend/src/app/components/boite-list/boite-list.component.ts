import { Component, OnInit } from '@angular/core';
import { BoiteService } from '../../services/boite.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { Boite } from '../../models/boite/boite.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boite-list',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './boite-list.component.html',
  styleUrls: ['./boite-list.component.css'],
})
export class BoiteListComponent implements OnInit {
  boites: Boite[] = [];
  displayedColumns: string[] = ['id', 'quantite', 'name', 'description', 'pointGeo', 'actions']; // Add 'actions'

  constructor(private boiteService: BoiteService, private router: Router) {}

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

  onUpdate(boite: Boite): void {
    this.router.navigate([`/editBoite/${boite.id}`]);
  }

  onDelete(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette boîte?')) {
      this.boiteService.deleteBoite(id).subscribe(
        () => {
          console.log('Boîte supprimée avec succès');
          this.boites = this.boites.filter((boite) => boite.id !== id); // Update the table
        },
        (error) => {
          console.error('Erreur lors de la suppression de la boîte', error);
        }
      );
    }
  }
  onAdd(): void {
    this.router.navigate(['/add']); // Navigate to the add form
  }
}
