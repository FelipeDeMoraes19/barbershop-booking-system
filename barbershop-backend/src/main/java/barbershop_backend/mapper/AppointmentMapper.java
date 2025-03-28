package barbershop_backend.mapper;

import barbershop_backend.dto.AppointmentDTO;
import barbershop_backend.model.Appointment;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "service", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "status", ignore = true)
    Appointment toEntity(AppointmentDTO appointmentDTO);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "service.id", target = "serviceId")
    AppointmentDTO toDto(Appointment appointment);
}