package barbershop_backend.mapper;

import barbershop_backend.dto.ClientDTO;
import barbershop_backend.model.Client;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDTO toDto(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "appointments", ignore = true) 
    Client toEntity(ClientDTO clientDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClientFromDto(ClientDTO clientDTO, @MappingTarget Client client);
}
