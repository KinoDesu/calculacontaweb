package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.List;

public interface OrderService {
    void saveNewOrder(OrderRequestDTO order);
    List<Order> getAllOrders();
}
