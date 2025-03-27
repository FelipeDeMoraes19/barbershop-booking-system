package barbershop_backend.service;

import barbershop_backend.dto.BarberServiceDTO;
import barbershop_backend.exception.ResourceNotFoundException;
import barbershop_backend.mapper.BarberServiceMapper;
import barbershop_backend.model.BarberServiceEntity;
import barbershop_backend.repository.BarberServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberServiceService {

    private final BarberServiceRepository serviceRepository;
    private final BarberServiceMapper serviceMapper;

    @Transactional
    public BarberServiceDTO createService(BarberServiceDTO serviceDTO) {
        BarberServiceEntity serviceEntity = serviceMapper.toEntity(serviceDTO);
        return serviceMapper.toDto(serviceRepository.save(serviceEntity));
    }

    public List<BarberServiceDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(serviceMapper::toDto)
                .toList();
    }

    public BarberServiceDTO getServiceById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));
    }

}