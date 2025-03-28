import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Client, ClientService } from '../../services/client.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-client-form',
  standalone: true, 
  imports: [CommonModule, ReactiveFormsModule], 
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.scss']
})
export class ClientFormComponent implements OnInit {
  form!: FormGroup;
  clientId?: number;

  constructor(
    private fb: FormBuilder,
    private clientService: ClientService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required]
    });

    this.clientId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.clientId) {
      this.clientService.getById(this.clientId).subscribe({
        next: (client: Client) => {
          this.form.patchValue(client);
        },
        error: (err: any) => console.error('Erro ao buscar cliente', err)
      });
    }
  }

  onSubmit(): void {
    if (this.form.invalid) return;
    const clientData: Client = this.form.value;

    if (this.clientId) {
      this.clientService.update(this.clientId, clientData).subscribe({
        next: () => {
          alert('Cliente atualizado com sucesso!');
          this.router.navigate(['/clientes']);
        },
        error: (err: any) => console.error('Erro ao atualizar cliente', err)
      });
    } else {
      this.clientService.create(clientData).subscribe({
        next: () => {
          alert('Cliente criado com sucesso!');
          this.router.navigate(['/clientes']);
        },
        error: (err: any) => console.error('Erro ao criar cliente', err)
      });
    }
  }
}
