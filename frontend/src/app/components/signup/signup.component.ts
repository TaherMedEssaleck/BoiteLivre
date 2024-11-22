import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Utilisateur } from '../../models/utilisateur.model';
import { FormsModule } from '@angular/forms';  // Importer FormsModule
import { CommonModule } from '@angular/common';  // Importer CommonModule

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, CommonModule],  // Ajouter CommonModule
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  utilisateur: Utilisateur = {
    id: 0,
    nom: '',
    prenom: '',
    mail: '',
    username: '',
    password: null
  };

  messageErreur: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  inscrire(): void {
    this.authService.inscrire(this.utilisateur).subscribe({
      next: (response) => {
        this.router.navigate(['/login']);
      },
      error: (err) => {
        this.messageErreur = 'Erreur lors de l\'inscription. Veuillez réessayer.';
      }
    });
  }
}
