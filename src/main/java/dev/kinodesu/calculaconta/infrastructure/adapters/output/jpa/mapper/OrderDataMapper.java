package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper;

import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientOrderData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = ClientOrderDataMapper.class)
public interface OrderDataMapper {

    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "itemName", target = "name")
    @Mapping(source = "itemQuantity", target = "quantity")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "tableData.tableId", target = "tableId")
    @Mapping(source = "clientOrderDataList", target = "clientOrderList")
    @Mapping(source = "orderData", target = "pricePerPerson")
    Order toEntity(OrderData orderData);

    List<Order> toEntity(List<OrderData> orderData);


    @Mapping(source = "orderId", target = "orderId")
    @Mapping(source = "name", target = "itemName")
    @Mapping(source = "quantity", target = "itemQuantity")
    @Mapping(source = "unitPrice", target = "unitPrice")
    @Mapping(source = "totalPrice", target = "totalPrice")
    @Mapping(source = "tableId", target = "tableData.tableId")
    @Mapping(target = "clientOrderDataList", ignore = true)
    OrderData toData(Order order);

    List<OrderData> toData(List<Order> order);


    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "tableData", ignore = true)
    @Mapping(target = "clientOrderDataList", ignore = true)
    @Mapping(target = "itemName", source = "name")
    @Mapping(target = "itemQuantity", source = "quantity")
    void updateData(
            Order order,
            @MappingTarget OrderData existingOrder
    );


    default List<UUID> mapClientOrderDataListToClientIds(
            List<ClientOrderData> clientOrderDataList
    ) {
        if (clientOrderDataList == null) {
            return List.of();
        }

        return clientOrderDataList.stream()
                .map(orderClient -> orderClient.getClientData().getClientId())
                .toList();
    }


    default BigDecimal calculatePricePerPerson(OrderData orderData) {

        if (orderData.getClientOrderDataList() == null
                || orderData.getClientOrderDataList().isEmpty()) {
            return BigDecimal.ZERO;
        }

        return orderData.getTotalPrice()
                .divide(
                        BigDecimal.valueOf(
                                orderData.getClientOrderDataList().size()
                        ),
                        2,
                        RoundingMode.HALF_UP
                );
    }
}