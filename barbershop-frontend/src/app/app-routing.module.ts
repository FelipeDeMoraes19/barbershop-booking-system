import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientListComponent } from './clients/client-list/client-list.component';
import { ClientFormComponent } from './clients/client-form/client-form.component';
import { BarberServiceListComponent } from './barber-services/barber-service-list/barber-service-list.component';
import { BarberServiceFormComponent } from './barber-services/barber-service-form/barber-service-form.component';

const routes: Routes = [
  { path: 'clientes', component: ClientListComponent },
  { path: 'clientes/novo', component: ClientFormComponent },
  { path: 'clientes/editar/:id', component: ClientFormComponent },

  { path: 'servicos', component: BarberServiceListComponent },
  { path: 'servicos/novo', component: BarberServiceFormComponent },

  { path: '', redirectTo: 'clientes', pathMatch: 'full' },
  { path: '**', redirectTo: 'clientes' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
