package barbershop_backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record BarberServiceDTO(
    @NotBlank(message = "Nome do serviço é obrigatório")
    String name,
    
    String description,
    
    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", message = "Preço deve ser positivo")
    BigDecimal price,
    
    @NotNull(message = "Duração é obrigatória")
    @Positive(message = "Duração deve ser maior que zero")
    Integer durationMinutes
) {}