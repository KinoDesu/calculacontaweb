package dev.kinodesu.calculaconta.infrastructure.adapters.output.websocket.impl;

import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.application.ports.output.WebSocketOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WebSocketAdapterImpl implements WebSocketOutputPort {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendNewOrderToTable(UUID tableId, Order order) {
        messagingTemplate.convertAndSend("/topic/add/room/" + tableId, order);
    }

    @Override
    public void sendOrderDeletionToClient(UUID tableId, UUID orderId) {
        messagingTemplate.convertAndSend("/topic/remove/room/" + tableId, orderId);
    }
}
