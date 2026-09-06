package dev.kinodesu.calculaconta.infra.mapper;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = ClientDataMapper.class)
public interface OrderDataMapper {

    @Mapping(source = "orderId",
            target = "orderId")
    @Mapping(source = "itemName",
            target = "name")
    @Mapping(source = "itemQuantity",
            target = "quantity")
    @Mapping(source = "personPrice",
            target = "pricePerPerson")
    @Mapping(source = "tableData.tableId", target = "tableId")
    @Mapping(source = "clientDataList", target = "clientList")
    Order toEntity(OrderData userData);

    List<Order> toEntity(List<OrderData> userData);


    @Mapping(source = "orderId",
            target = "orderId")
    @Mapping(source = "name",
            target = "itemName")
    @Mapping(source = "quantity",
            target = "itemQuantity")
    @Mapping(source = "pricePerPerson",
            target = "personPrice")
    @Mapping(source = "tableId", target = "tableData.tableId")
    @Mapping(source = "clientList", target = "clientDataList")
    OrderData toData(Order user);

    List<OrderData> toData(List<Order> user);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "tableData", ignore = true)
    @Mapping(target = "clientDataList", ignore = true)
    void updateDate(Order order, @MappingTarget OrderData existingOrder);
}
