import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BarberService, BarberServiceService } from '../../services/barber-service.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-barber-service-form',
  templateUrl: './barber-service-form.component.html',
  styleUrls: ['./barber-service-form.component.scss']
})
export class BarberServiceFormComponent implements OnInit {
  form!: FormGroup;
  serviceId?: number;

  constructor(
    private fb: FormBuilder,
    private serviceService: BarberServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      name: ['', Validators.required],
      description: [''],
      price: [0, [Validators.required]],
      durationMinutes: [0, [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const serviceData: BarberService = this.form.value;

    this.serviceService.create(serviceData).subscribe({
      next: () => {
        alert('Serviço criado com sucesso!');
        this.router.navigate(['/servicos']);
      },
      error: (err: any) => console.error('Erro ao criar serviço', err)
    });
  }
}
