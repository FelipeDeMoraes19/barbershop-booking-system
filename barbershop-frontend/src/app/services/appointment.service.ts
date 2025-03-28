import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL = '/api';

export interface Appointment {
  id?: number;
  clientId: number;
  serviceId: number;
  startTime: string; 
  status?: string;   
}

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${BASE_URL}/agendamentos`);
  }

  getById(id: number): Observable<Appointment> {
    return this.http.get<Appointment>(`${BASE_URL}/agendamentos/${id}`);
  }

  create(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(`${BASE_URL}/agendamentos`, appointment);
  }

  update(id: number, appointment: Appointment): Observable<Appointment> {
    return this.http.put<Appointment>(`${BASE_URL}/agendamentos/${id}`, appointment);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${BASE_URL}/agendamentos/${id}`);
  }
}
