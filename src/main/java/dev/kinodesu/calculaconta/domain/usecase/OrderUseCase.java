package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;

public interface OrderUseCase {
    void saveNewOrder(Order order);
    List<Order> getAllOrdersByRoomCode(String roomCode);

    void deleteOrder(String orderId);
}
