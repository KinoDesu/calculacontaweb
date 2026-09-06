package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {

    Order saveOrder(Order order);

    List<Order> listTableOrders(UUID tableId);

    void deleteOrder(UUID orderId);

    void clearTableOrders(UUID tableId);
}
