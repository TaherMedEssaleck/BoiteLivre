import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boite } from '../models/boite.model';

@Injectable({
  providedIn: 'root'
})
export class BoiteService {
  private apiUrl = 'http://localhost:8080/api/boites/'; 

  constructor(private http: HttpClient) { }

  // Get all boites
  getBoites(): Observable<Boite[]> {
    return this.http.get<Boite[]>(this.apiUrl);
  }

  // Get a single boite by ID
  getBoiteById(id: number): Observable<Boite> {
    return this.http.get<Boite>(`${this.apiUrl}/${id}`);
  }

  // Create a new boite
  createBoite(boite: Boite): Observable<Boite> {
    return this.http.post<Boite>(this.apiUrl, boite, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // Update an existing boite
  updateBoite(boite: Boite): Observable<Boite> {
    return this.http.put<Boite>(`${this.apiUrl}/${boite.id}`, boite, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // Delete a boite
  deleteBoite(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
