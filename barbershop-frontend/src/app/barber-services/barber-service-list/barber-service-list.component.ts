import { Component, OnInit } from '@angular/core';
import { BarberService, BarberServiceService } from 'src/app/services/barber-service.service';

@Component({
  selector: 'app-barber-service-list',
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
      next: (data) => {
        this.services = data;
      },
      error: (err) => console.error('Erro ao buscar serviços', err)
    });
  }
}
