package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.dataprovider.OrderDataProvider;
import dev.kinodesu.calculaconta.infra.mapper.OrderDataMapper;
import dev.kinodesu.calculaconta.infra.repository.OrderRepository;
import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import dev.kinodesu.calculaconta.infra.websocket.WebSocketProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDataProviderImpl implements OrderDataProvider {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;
    private final WebSocketProvider webSocketProvider;

    @Override
    public void saveNewOrder(Order order) {
        OrderData orderData = orderDataMapper.toData(order);
        orderRepository.save(orderData);
    }

    @Override
    public List<Order> getAllOrdersByRoomCode(String roomCode) {
        List<OrderData> userDataList = orderRepository.findAllByRoomCode(roomCode);
        return orderDataMapper.toEntity(userDataList);
    }

    @Override
    public void sendToClient(Order order) {
        webSocketProvider.sendOrderToRoom(order.getRoom().getCode(), order);
    }
}
