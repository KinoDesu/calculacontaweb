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
    public void sendOrderToRoom(String roomCode, Order order) {
        messagingTemplate.convertAndSend("/topic/room/" + order.getRoom().getCode(), order);
    }
}
