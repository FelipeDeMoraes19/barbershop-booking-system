import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL = '/api';

export interface BarberService {
  id?: number;
  name: string;
  description?: string;
  price: number;
  durationMinutes: number;
}

@Injectable({
  providedIn: 'root'
})
export class BarberServiceService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<BarberService[]> {
    return this.http.get<BarberService[]>(`${BASE_URL}/servicos`);
  }

  getById(id: number): Observable<BarberService> {
    return this.http.get<BarberService>(`${BASE_URL}/servicos/${id}`);
  }

  create(service: BarberService): Observable<BarberService> {
    return this.http.post<BarberService>(`${BASE_URL}/servicos`, service);
  }

}
