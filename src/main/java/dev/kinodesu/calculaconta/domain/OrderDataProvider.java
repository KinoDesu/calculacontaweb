package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDataProvider {
    Order saveOrder(Order order);

    List<Order> getAllOrdersByTableId(UUID tableId);

    void deleteOrder(UUID orderId);

    void sendNewOrderToClient(Order order);

    void sendOrderDeletionToClient(Order order);

    Order getOrderbyId(UUID orderId);

    void deleteAllOrderByTableId(UUID tableId);
}
