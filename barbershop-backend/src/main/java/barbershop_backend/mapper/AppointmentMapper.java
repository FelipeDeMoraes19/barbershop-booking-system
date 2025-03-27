package barbershop_backend.mapper;

import barbershop_backend.dto.AppointmentDTO;
import barbershop_backend.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClientMapper.class, BarberServiceMapper.class})
public interface AppointmentMapper {
    
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "serviceId", target = "service.id")
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "service.id", target = "serviceId")
    AppointmentDTO toDto(Appointment appointment);
}