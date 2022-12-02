import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApprovissionnementService {
   private baseURL ="http://localhost:9191/api/v1/approvisionnements"
  constructor(private httpClient:HttpClient ) { }
}
