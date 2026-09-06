package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.OrderDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;

import java.util.List;
import java.util.UUID;

public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderDataProvider orderDataProvider;

    public OrderUseCaseImpl(OrderDataProvider orderDataProvider) {
        this.orderDataProvider = orderDataProvider;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderDataProvider.saveOrder(order);
    }

    @Override
    public List<Order> listTableOrders(UUID tableId) {
        return orderDataProvider.getAllOrdersByTableId(tableId);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderDataProvider.deleteOrder(orderId);
    }

    @Override
    public void clearTableOrders(UUID tableId) {
        orderDataProvider.deleteAllOrderByTableId(tableId);
    }
}
