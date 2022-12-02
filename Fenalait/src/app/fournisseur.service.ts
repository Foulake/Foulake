import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FournisseurService {
   private baseURL="http://localhost:9191/api/v1/fournisseurs"
  constructor(private httpClient :HttpClient) { }
}
