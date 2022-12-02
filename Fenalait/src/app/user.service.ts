import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
   private baseURL ="http://localhost:9191/api/v1/users"
  constructor(private httpClient: HttpClient) { }
}
