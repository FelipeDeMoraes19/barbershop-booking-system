package barbershop_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientDTO(
    @NotBlank(message = "Nome é obrigatório")
    String name,
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    String email,
    
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(
        regexp = "^\\(?(\\d{2})\\)?[\\s-]?(9?\\d{4})[-\\s]?(\\d{4})$",
        message = "Formato inválido. Use (XX) XXXXX-XXXX"
    )
    String phone
) {}