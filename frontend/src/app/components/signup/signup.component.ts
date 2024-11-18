import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service'; 
import { Utilisateur } from '../../models/utilisateur.model'; 

@Component({
  selector: 'app-signup',
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
