package dev.kinodesu.calculaconta.infra.websocket.impl;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.websocket.WebSocketProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class WebSocketProviderImpl implements WebSocketProvider {

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
