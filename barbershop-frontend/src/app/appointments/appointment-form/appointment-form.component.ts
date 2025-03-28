import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Appointment, AppointmentService } from '../../services/appointment.service';
import { Client, ClientService } from '../../services/client.service';
import { BarberService, BarberServiceService } from '../../services/barber-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-appointment-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './appointment-form.component.html',
  styleUrls: ['./appointment-form.component.scss']
})
export class AppointmentFormComponent implements OnInit {
  form!: FormGroup;
  appointmentId?: number;
  clients: Client[] = [];
  services: BarberService[] = [];

  constructor(
    private fb: FormBuilder,
    private appointmentService: AppointmentService,
    private clientService: ClientService,
    private barberServiceService: BarberServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.clientService.getAll().subscribe({
      next: (data: Client[]) => this.clients = data,
      error: (err: any) => console.error('Erro ao buscar clientes', err)
    });

    this.barberServiceService.getAll().subscribe({
      next: (data: BarberService[]) => this.services = data,
      error: (err: any) => console.error('Erro ao buscar serviços', err)
    });

    this.form = this.fb.group({
      clientId: [null, Validators.required],
      serviceId: [null, Validators.required],
      startTime: ['', Validators.required],
      status: ['AGENDADO']
    });

    this.appointmentId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.appointmentId) {
      this.appointmentService.getById(this.appointmentId).subscribe({
        next: (appointment: Appointment) => {
          this.form.patchValue(appointment);
        },
        error: (err: any) => console.error('Erro ao buscar agendamento', err)
      });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    const data: Appointment = this.form.value;

    if (this.appointmentId) {
      this.appointmentService.update(this.appointmentId, data).subscribe({
        next: () => {
          alert('Agendamento atualizado com sucesso!');
          this.router.navigate(['/agendamentos']);
        },
        error: (err: any) => console.error('Erro ao atualizar agendamento', err)
      });
    } else {
      this.appointmentService.create(data).subscribe({
        next: () => {
          alert('Agendamento criado com sucesso!');
          this.router.navigate(['/agendamentos']);
        },
        error: (err: any) => console.error('Erro ao criar agendamento', err)
      });
    }
  }
}
