package barbershop_backend.controller;

import barbershop_backend.dto.BarberServiceDTO;
import barbershop_backend.service.BarberServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
@Tag(name = "Serviços", description = "Gerenciamento de serviços da barbearia")
@CrossOrigin(origins = "http://localhost:4200")
public class BarberServiceController {

    private final BarberServiceService serviceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo serviço")
    public BarberServiceDTO createService(@Valid @RequestBody BarberServiceDTO serviceDTO) {
        return serviceService.createService(serviceDTO);
    }

    @GetMapping
    @Operation(summary = "Lista todos os serviços")
    public List<BarberServiceDTO> getAllServices() {
        return serviceService.getAllServices();
    }
}