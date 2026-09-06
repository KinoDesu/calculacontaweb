package dev.kinodesu.calculaconta.infra.mapper;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ClientDataMapper.class)
public interface OrderDataMapper {

    @Mapping(source = "orderId",
            target = "orderId")
    Order toEntity(OrderData userData);

    List<Order> toEntity(List<OrderData> userData);

    @Mapping(source = "orderId",
            target = "orderId")
    OrderData toData(Order user);

    List<OrderData> toData(List<Order> user);
}
