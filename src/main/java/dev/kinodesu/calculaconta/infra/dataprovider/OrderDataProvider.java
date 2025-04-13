package dev.kinodesu.calculaconta.infra.dataprovider;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;

public interface OrderDataProvider {
    void saveNewOrder(Order order);
    List<Order> getAllOrdersByRoomCode(String roomCode);

    void sendToClient(Order order);
}
