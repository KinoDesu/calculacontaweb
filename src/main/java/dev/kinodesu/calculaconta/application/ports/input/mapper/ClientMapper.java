package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.ClientResponseDTO;
import dev.kinodesu.calculaconta.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "tableId", source = "tableId")
    ClientResponseDTO toResponse(Client client);

    List<ClientResponseDTO> toResponse(List<Client> client);

    @Mapping(target = "clientId", source = "clientRequestDTO.clientId")
    @Mapping(target = "name", source = "clientRequestDTO.name")
    @Mapping(target = "tableId", source = "tableId")
    Client toEntity(ClientRequestDTO clientRequestDTO, UUID tableId);
}
