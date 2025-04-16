package dev.kinodesu.calculaconta.infra.websocket.impl;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.websocket.WebSocketProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketProviderImpl implements WebSocketProvider {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendNewOrderToRoom(String roomCode, Order order) {
        messagingTemplate.convertAndSend("/topic/add/room/" + order.getRoom().getCode(), order);
    }

    @Override
    public void sendOrderDeletionToClient(String roomCode, String orderId) {
        messagingTemplate.convertAndSend("/topic/remove/room/" + roomCode, orderId);
    }
}
