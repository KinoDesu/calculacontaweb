package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.model.ClientOrder;
import dev.kinodesu.calculaconta.domain.entity.ClientOrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientOrderMapper {

    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "clientName", source = "name")
    @Mapping(target = "amount", source = "amount")
    ClientOrderResponseDTO toResponse(ClientOrder clientOrder);

    List<ClientOrderResponseDTO> toResponseList(List<ClientOrder> clientOrderList);
}
