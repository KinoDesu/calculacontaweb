package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderDataProvider {
    void saveNewOrder(Order order);
    List<Order> getAllOrdersByRoomCode(String roomCode);

    void deleteOrder(Order order);

    void sendNewOrderToClient(Order order);

    void sendOrderDeletionToClient(Order order);

    Order getOrderbyId(UUID orderId);
}
