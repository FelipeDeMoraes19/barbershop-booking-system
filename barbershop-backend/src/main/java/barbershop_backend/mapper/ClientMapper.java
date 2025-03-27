package barbershop_backend.mapper;

import barbershop_backend.dto.ClientDTO;
import barbershop_backend.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
    ClientDTO toDto(Client client);
    Client toEntity(ClientDTO clientDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
void updateClientFromDto(ClientDTO clientDTO, @MappingTarget Client client);
}