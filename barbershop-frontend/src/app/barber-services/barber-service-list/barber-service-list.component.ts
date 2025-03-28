import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { BarberService, BarberServiceService } from '../../services/barber-service.service';

@Component({
  selector: 'app-barber-service-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './barber-service-list.component.html',
  styleUrls: ['./barber-service-list.component.scss']
})
export class BarberServiceListComponent implements OnInit {
  services: BarberService[] = [];

  constructor(private serviceService: BarberServiceService) {}

  ngOnInit(): void {
    this.loadServices();
  }

  loadServices(): void {
    this.serviceService.getAll().subscribe({
      next: (data: BarberService[]) => {
        this.services = data;
      },
      error: (err: any) => console.error('Erro ao buscar serviços', err)
    });
  }
}
