import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boite } from '../models/boite/boite.module';

@Injectable({
  providedIn: 'root',
})
export class BoiteService {
  private apiUrl = 'http://localhost:8080/api/boites/';

  constructor(private http: HttpClient) {}

  // Récupérer toutes les boîtes
  getBoites(): Observable<Boite[]> {
    return this.http.get<Boite[]>(`${this.apiUrl}all`);
  }

  // Récupérer une boîte par ID
  getBoiteById(id: number): Observable<Boite> {
    return this.http.get<Boite>(`${this.apiUrl}${id}`);
  }

  // Créer une nouvelle boîte
  createBoite(boite: Boite): Observable<Boite> {
    return this.http.post<Boite>(`${this.apiUrl}create`, boite);
  }

  // Mettre à jour une boîte
  updateBoite(boite: Boite): Observable<Boite> {
    return this.http.put<Boite>(`${this.apiUrl}update`, boite);
  }

  // Supprimer une boîte par ID
  deleteBoite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}delete/${id}`);
  }
}
