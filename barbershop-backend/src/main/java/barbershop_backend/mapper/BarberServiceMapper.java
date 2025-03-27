package barbershop_backend.mapper;

import barbershop_backend.dto.BarberServiceDTO;
import barbershop_backend.model.BarberServiceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BarberServiceMapper {
    BarberServiceDTO toDto(BarberServiceEntity service);
    BarberServiceEntity toEntity(BarberServiceDTO serviceDTO);
}