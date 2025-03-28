package barbershop_backend.dto;

import barbershop_backend.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(
    Long id,

    @NotNull(message = "ID do cliente é obrigatório")
    Long clientId,

    @NotNull(message = "ID do serviço é obrigatório")
    Long serviceId,

    @NotNull(message = "Data/hora inicial é obrigatória")
    LocalDateTime startTime,

    AppointmentStatus status
) {}
