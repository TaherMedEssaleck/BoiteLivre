import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { Utilisateur } from '../models/utilisateur.model'; 


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  inscrire(utilisateur: Utilisateur): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, utilisateur);
  }

  seConnecter(username: string, password: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, { username, password });
  }

  estAuthentifie(): boolean {
    return !!localStorage.getItem('token');
  }
  
  seDeconnecter(): void {
    localStorage.removeItem('token');
  }
}
