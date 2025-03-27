package barbershop_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cliente é obrigatório")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull(message = "Serviço é obrigatório")
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private BarberService service;

    @NotNull(message = "Data/hora de início é obrigatória")
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "Data/hora de término é obrigatória")
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;
}