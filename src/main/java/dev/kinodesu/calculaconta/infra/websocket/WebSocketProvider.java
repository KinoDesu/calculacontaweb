package dev.kinodesu.calculaconta.infra.websocket;

import dev.kinodesu.calculaconta.domain.entity.Order;

public interface WebSocketProvider {
    void sendNewOrderToRoom(String roomCode, Order order);

    void sendOrderDeletionToClient(String roomCode, String orderId);
}
