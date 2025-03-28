import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

import { Client, ClientService } from '../../services/client.service';

@Component({
  selector: 'app-client-list',
  standalone: true, 
  imports: [CommonModule, RouterLink], 
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  clients: Client[] = [];

  constructor(private clientService: ClientService) {}

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getAll().subscribe({
      next: (data: Client[]) => {
        this.clients = data;
      },
      error: (err: any) => {
        console.error('Erro ao buscar clientes', err);
      }
    });
  }

  deleteClient(id?: number): void {
    if (!id) return;
    const confirmDelete = confirm('Deseja excluir este cliente?');
    if (confirmDelete) {
      this.clientService.delete(id).subscribe({
        next: () => this.loadClients(),
        error: (err: any) => console.error('Erro ao excluir cliente', err)
      });
    }
  }
}
