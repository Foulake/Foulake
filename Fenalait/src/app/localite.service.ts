import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocaliteService {
    private baseURL ="http://localhost:9191/api/v1/localites"
  constructor(private httpClient: HttpClient) { }
}
