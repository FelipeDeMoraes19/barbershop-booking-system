package barbershop_backend.mapper;

import barbershop_backend.dto.BarberServiceDTO;
import barbershop_backend.model.BarberServiceEntity;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BarberServiceMapper {

    BarberServiceDTO toDto(BarberServiceEntity service);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    BarberServiceEntity toEntity(BarberServiceDTO serviceDTO);
}