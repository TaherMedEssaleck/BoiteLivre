import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';  // Importer FormsModule
import { CommonModule } from '@angular/common';  // Importer CommonModule

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],  // Ajouter CommonModule
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  messageErreur: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  seConnecter(): void {
    this.authService.seConnecter(this.username, this.password).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.messageErreur = 'Nom d\'utilisateur ou mot de passe incorrect.';
      }
    });
  }
}
