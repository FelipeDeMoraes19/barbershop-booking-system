import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { Appointment, AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-appointment-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.scss']
})
export class AppointmentListComponent implements OnInit {
  appointments: Appointment[] = [];

  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments(): void {
    this.appointmentService.getAll().subscribe({
      next: (data: Appointment[]) => {
        this.appointments = data;
      },
      error: (err: any) => console.error('Erro ao buscar agendamentos', err)
    });
  }

  deleteAppointment(id?: number): void {
    if (!id) return;
    const confirmDelete = confirm('Deseja excluir este agendamento?');
    if (confirmDelete) {
      this.appointmentService.delete(id).subscribe({
        next: () => this.loadAppointments(),
        error: (err: any) => console.error('Erro ao excluir agendamento', err)
      });
    }
  }
}
