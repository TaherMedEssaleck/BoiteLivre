import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boite } from '../models/boite/boite.module';

@Injectable({
  providedIn: 'root', // Indique que ce service est accessible globalement
})
export class BoiteService {
  private apiUrl = 'http://localhost:8080/api/boites/all'; // URL de votre API Spring Boot

  constructor(private http: HttpClient) {}

  getBoites(): Observable<Boite[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}
