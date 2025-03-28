import { Component, OnInit } from '@angular/core';
import { Appointment, AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-appointment-list',
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
      next: (data) => {
        this.appointments = data;
      },
      error: (err) => console.error('Erro ao buscar agendamentos', err)
    });
  }

  deleteAppointment(id?: number): void {
    if (!id) return;
    const confirmDelete = confirm('Deseja excluir este agendamento?');
    if (confirmDelete) {
      this.appointmentService.delete(id).subscribe({
        next: () => this.loadAppointments(),
        error: (err) => console.error('Erro ao excluir agendamento', err)
      });
    }
  }
}
