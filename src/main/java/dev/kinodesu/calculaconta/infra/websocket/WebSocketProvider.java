package dev.kinodesu.calculaconta.infra.websocket;

import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.UUID;

public interface WebSocketProvider {
    void sendNewOrderToTable(UUID tableId, Order order);

    void sendOrderDeletionToClient(UUID tableId, UUID orderId);
}
