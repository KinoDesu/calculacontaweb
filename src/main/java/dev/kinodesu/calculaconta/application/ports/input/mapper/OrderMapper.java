package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;
import dev.kinodesu.calculaconta.domain.model.ClientOrder;
import dev.kinodesu.calculaconta.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = ClientOrderMapper.class)
public interface OrderMapper {
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "unitPrice", source = "unitPrice")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "totalPrice", source = "totalPrice")
    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "pricePerPerson", source = "pricePerPerson")
    @Mapping(target = "clients", source = "clientOrderList")
    OrderResponseDTO toResponse(Order order);

    @Mapping(target = "orderId", source = "orderRequestDTO.orderId")
    @Mapping(target = "name", source = "orderRequestDTO.name")
    @Mapping(target = "unitPrice", source = "orderRequestDTO.unitPrice")
    @Mapping(target = "quantity", source = "orderRequestDTO.quantity")
    @Mapping(target = "totalPrice", source = "totalAmount")
    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "pricePerPerson", source = "pricePerPerson")
    @Mapping(target = "clientOrderList", source = "orderRequestDTO.clientList", qualifiedByName = "mapClientOrderIdList")
    Order toEntity(OrderRequestDTO orderRequestDTO, UUID tableId, BigDecimal totalAmount, BigDecimal pricePerPerson);

    @Named("mapClientOrderIdList")
    default List<ClientOrder> mapClientOrderIdList(List<UUID> clientIdList) {
        List<ClientOrder> finalList = new ArrayList<>(1);
        for (UUID id : clientIdList) {
            finalList.add(ClientOrder.builder()
                    .clientId(id)
                    .build());
        }
        return finalList;
    }
}
