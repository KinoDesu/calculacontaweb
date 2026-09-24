package dev.kinodesu.calculaconta.application.ports.output;

import dev.kinodesu.calculaconta.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderOutputPort {
    void deleteOrder(UUID orderId);

    Order findById(UUID orderId);

    Order createOrUpdateTableOrder(Order order);

    List<Order> findAllByTableId(UUID tableId);
}
