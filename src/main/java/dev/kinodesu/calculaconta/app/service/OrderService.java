package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.Order;

import java.util.UUID;

public interface OrderService {
    Order saveOrder(OrderRequestDTO orderRequestDTO);

    Order listTableOrders(UUID tableId);

    Order deleteOrder(UUID orderId);

    Order clearTableOrders(UUID tableId);
}
