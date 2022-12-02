import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseURL ="http://localhost:9191/api/v1/categories"
  constructor( private httpClient :HttpClient) { }
}
