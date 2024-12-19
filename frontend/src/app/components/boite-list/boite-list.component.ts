import { Component, OnInit } from '@angular/core';
import { BoiteService } from '../../services/boite.service';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { FormsModule } from '@angular/forms';
import { Boite } from '../../models/boite/boite.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boite-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, FormsModule],
  templateUrl: './boite-list.component.html',
  styleUrls: ['./boite-list.component.css'],
})
export class BoiteListComponent implements OnInit {
  boites: Boite[] = []; 
  displayedColumns: string[] = ['id', 'quantite', 'name', 'description', 'pointGeo', 'actions']; // Colonnes affichées

  filteredBoites: Boite[] = []; 
  searchText: string = '';
  sortDirection: 'asc' | 'desc' = 'asc'; 

  constructor(private boiteService: BoiteService, private router: Router) {}

  ngOnInit(): void {
    this.boiteService.getBoites().subscribe(
      (data) => {
        this.boites = data; 
        this.filteredBoites = [...this.boites];
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
          this.boites = this.boites.filter((boite) => boite.id !== id); 
          this.applyFilters(); 
        },
        (error) => {
          console.error('Erreur lors de la suppression de la boîte', error);
        }
      );
    }
  }

  // Méthode pour ajouter une boîte
  onAdd(): void {
    this.router.navigate(['/add']);
  }

  // Méthode pour naviguer vers la carte
  carte(): void {
    this.router.navigate(['/map']);
  }

  // Méthode pour naviguer vers les détails d'une boîte
  onRowClick(id: number): void {
    this.router.navigate([`/boite-detail/${id}`]);
  }


  sort(column: keyof Boite): void {
    console.log('Clicked on column:', column);
    this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
  
    // Crée une nouvelle référence pour filteredBoites après le tri
    this.filteredBoites = [...this.boites].sort((a, b) => {
      const valA = a[column];
      const valB = b[column];
  
      if (typeof valA === 'number' && typeof valB === 'number') {
        return this.sortDirection === 'asc' ? valA - valB : valB - valA;
      }
  
      if (typeof valA === 'string' && typeof valB === 'string') {
        return this.sortDirection === 'asc'
          ? valA.localeCompare(valB)
          : valB.localeCompare(valA);
      }
  
      return 0;
    });
  }
  

  applyFilters(): void {
    this.filteredBoites = this.boites.filter((boite) =>
      Object.values(boite).some((val) =>
        val.toString().toLowerCase().includes(this.searchText.toLowerCase())
      )
    );
    this.sort('id'); // Tri par défaut après filtrage
  }
}
