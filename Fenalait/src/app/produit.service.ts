import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProduitService {
  private baseURL= "http://localhost:9191/api/v1/produits"
  constructor( private httpClient:HttpClient) { }
}
