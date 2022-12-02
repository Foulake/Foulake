import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeService {
   private baseURL="http://localhost:9191/api/v1/employes"
  constructor(private httpClient :HttpClient) { }
}
