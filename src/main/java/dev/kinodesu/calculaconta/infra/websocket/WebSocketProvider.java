package dev.kinodesu.calculaconta.infra.websocket;

import dev.kinodesu.calculaconta.domain.entity.Order;

public interface WebSocketProvider {
    void sendOrderToRoom(String roomCode, Order order);
}
