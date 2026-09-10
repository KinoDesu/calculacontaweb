package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper;

import dev.kinodesu.calculaconta.domain.model.Client;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientDataMapper {

    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "tableData.tableId", target = "tableId")
    @Mapping(source = "bot", target = "isBot")
    Client toEntity(ClientData clientData);

    List<Client> toEntity(List<ClientData> clientData);


    @Mapping(source = "clientId", target = "clientId")
    @Mapping(source = "tableId", target = "tableData.tableId")
    @Mapping(source = "bot", target = "isBot")
    ClientData toData(Client client);

    @Mapping(target = "clientId", ignore = true)
    @Mapping(target = "tableData", ignore = true)
    @Mapping(target = "clientOrderDataList", ignore = true)
    void updateData(
            Client client,
            @MappingTarget ClientData existingClient
    );
}