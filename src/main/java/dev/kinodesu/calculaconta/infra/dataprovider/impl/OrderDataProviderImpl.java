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
    public Order saveOrder(Order order) {

        if(order.getOrderId()!=null){
            OrderData existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow();
            orderDataMapper.updateDate(order, existingOrder);

            return orderDataMapper.toEntity(orderRepository.save(existingOrder));
        }

        OrderData orderData = orderDataMapper.toData(order);
        orderData.setOrderId(UUID.randomUUID());
        return orderDataMapper.toEntity(orderRepository.save(orderData));
    }

    @Override
    public List<Order> getAllOrdersByTableId(UUID tableId) {
        List<OrderData> userDataList = orderRepository.findAllByTableId(tableId);
        return orderDataMapper.toEntity(userDataList);
    }


    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public void sendNewOrderToClient(Order order) {
        webSocketProvider.sendNewOrderToTable(order.getTableId(), order);
    }

    @Override
    public void sendOrderDeletionToClient(Order order) {
        webSocketProvider.sendOrderDeletionToClient(order.getTableId(), order.getOrderId());
    }

    @Override
    public Order getOrderbyId(UUID orderId) {
        return orderDataMapper.toEntity(orderRepository.findById(orderId).orElseThrow());
    }

    @Override
    public void deleteAllOrderByTableId(UUID tableId) {
        orderRepository.deleteAllByTableId(tableId);
    }
}
