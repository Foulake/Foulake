import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MagasinService {
   private baseURL ="http://localhost:9191/api/v1/magasins"
  constructor(private httpClient: HttpClient) { }
}
