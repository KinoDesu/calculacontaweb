package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.model.ClientOrder;
import dev.kinodesu.calculaconta.domain.entity.ClientOrderResponseDTO;
import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = ClientOrderMapper.class)
public interface OrderMapper {
    @Mapping(target = "orderId", source = "order.orderId")
    @Mapping(target = "name", source = "order.name")
    @Mapping(target = "unitPrice", source = "order.unitPrice")
    @Mapping(target = "quantity", source = "order.quantity")
    @Mapping(target = "totalPrice", source = "order.totalPrice")
    @Mapping(target = "tableId", source = "order.tableId")
    @Mapping(target = "pricePerPerson", source = "order.pricePerPerson")
    @Mapping(target = "clients", source = "clientOrderList")
    OrderResponseDTO toResponse(Order order, List<ClientOrder> clientOrderList);

    @Mapping(target = "orderId", source = "orderRequestDTO.orderId")
    @Mapping(target = "name", source = "orderRequestDTO.name")
    @Mapping(target = "unitPrice", source = "orderRequestDTO.unitPrice")
    @Mapping(target = "quantity", source = "orderRequestDTO.quantity")
    @Mapping(target = "totalPrice", source = "totalAmount")
    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "pricePerPerson", source = "pricePerPerson")
    @Mapping(target = "clientIdList", source = "orderRequestDTO.clientList")
    Order toEntity(OrderRequestDTO orderRequestDTO, UUID tableId, BigDecimal totalAmount, BigDecimal pricePerPerson);
}
