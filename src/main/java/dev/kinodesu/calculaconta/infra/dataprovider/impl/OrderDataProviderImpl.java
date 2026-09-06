package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.OrderDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.infra.mapper.OrderDataMapper;
import dev.kinodesu.calculaconta.infra.repository.OrderRepository;
import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import dev.kinodesu.calculaconta.infra.websocket.WebSocketProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

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
        List<OrderData> userDataList = orderRepository.findAllByTableCode(roomCode);
        return orderDataMapper.toEntity(userDataList);
    }


    @Override
    public void deleteOrder(Order order) {
        orderRepository.deleteById(order.getOrderId());
    }

    @Override
    public void sendNewOrderToClient(Order order) {
        webSocketProvider.sendNewOrderToRoom(order.getTable().getCode(), order);
    }

    @Override
    public void sendOrderDeletionToClient(Order order) {
        webSocketProvider.sendOrderDeletionToClient(order.getTable().getCode(), order.getOrderId());
    }

    @Override
    public Order getOrderbyId(UUID orderId) {
        return orderDataMapper.toEntity(orderRepository.findById(orderId).orElseThrow());
    }
}
