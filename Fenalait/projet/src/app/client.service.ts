import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Client } from './client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  deleteClient(id: number) {
    throw new Error('Method not implemented.');
  }
  private baseURL="http://localhost:8181/api/v1/clients";

  constructor(private httpClient:HttpClient) { }
  getClientList(): Observable<Client[]>{
    return this.httpClient.get<Client[]>(`${this.baseURL}`);
  }
}
 