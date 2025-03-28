import { Routes } from '@angular/router';

import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientFormComponent } from './clients/client-form/client-form.component';
import { BarberServiceListComponent } from './barber-services/barber-service-list/barber-service-list.component';
import { BarberServiceFormComponent } from './barber-services/barber-service-form/barber-service-form.component';
import { AppointmentListComponent } from './appointments/appointment-list/appointment-list.component';
import { AppointmentFormComponent } from './appointments/appointment-form/appointment-form.component';

export const routes: Routes = [
  { path: 'clientes', component: ClientListComponent },
  { path: 'clientes/novo', component: ClientFormComponent },
  { path: 'clientes/editar/:id', component: ClientFormComponent },

  { path: 'servicos', component: BarberServiceListComponent },
  { path: 'servicos/novo', component: BarberServiceFormComponent },

  { path: 'agendamentos', component: AppointmentListComponent },
  { path: 'agendamentos/novo', component: AppointmentFormComponent },
  { path: 'agendamentos/editar/:id', component: AppointmentFormComponent },

  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  { path: '**', redirectTo: 'clientes' }
];
