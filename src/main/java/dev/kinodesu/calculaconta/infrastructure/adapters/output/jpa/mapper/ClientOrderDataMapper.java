package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper;

import dev.kinodesu.calculaconta.domain.model.ClientOrder;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientOrderData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientOrderDataMapper {

    @Mapping(target = "clientId", source = "orderClientId.clientId")
    @Mapping(target = "name", source = "clientData.name")
    @Mapping(target = "amount", source = "value")
    ClientOrder toEntity(ClientOrderData clientOrderData);
}
