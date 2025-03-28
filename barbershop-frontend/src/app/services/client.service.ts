import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL = '/api';

export interface Client {
  id?: number;
  name: string;
  email: string;
  phone: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Client[]> {
    return this.http.get<Client[]>(`${BASE_URL}/clientes`);
  }

  getById(id: number): Observable<Client> {
    return this.http.get<Client>(`${BASE_URL}/clientes/${id}`);
  }

  create(client: Client): Observable<Client> {
    return this.http.post<Client>(`${BASE_URL}/clientes`, client);
  }

  update(id: number, client: Client): Observable<Client> {
    return this.http.put<Client>(`${BASE_URL}/clientes/${id}`, client);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/clientes/${id}`);
  }
}
