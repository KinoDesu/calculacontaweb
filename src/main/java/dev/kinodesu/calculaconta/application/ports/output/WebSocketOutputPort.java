package dev.kinodesu.calculaconta.application.ports.output;

import dev.kinodesu.calculaconta.domain.model.Order;

import java.util.UUID;

public interface WebSocketOutputPort {
    void sendNewOrderToTable(UUID tableId, Order order);

    void sendOrderDeletionToClient(UUID tableId, UUID orderId);
}
