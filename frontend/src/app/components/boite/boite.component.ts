import { Component, OnInit } from '@angular/core';
import { BoiteService } from '../../services/boite.service';
import { Boite } from '../../models/boite.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-boite',
  templateUrl: './boite.component.html',
  styleUrls: ['./boite.component.css']
})
export class BoiteComponent implements OnInit {
  boites: Boite[] = [];
  
  // Make router public so it can be used in the template
  constructor(public router: Router, private boiteService: BoiteService) {}

  ngOnInit(): void {
    this.getBoites();
  }

  // Get all boites
  getBoites(): void {
    this.boiteService.getBoites().subscribe((boites) => {
      this.boites = boites;
    });
  }

  // View a boite's details
  viewBoite(id: number): void {
    this.router.navigate([`/boites/${id}`]);
  }

  // Delete a boite
  deleteBoite(id: number): void {
    this.boiteService.deleteBoite(id).subscribe(() => {
      this.getBoites();
    });
  }
}
