package dev.kinodesu.calculaconta.app.controller;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OrderController {

    ResponseEntity<Object> saveOrder(OrderRequestDTO orderRequestDTO);

    ResponseEntity<Object> listTableOrders(UUID tableId);

    ResponseEntity<Object> deleteOrder(UUID orderId);

    ResponseEntity<Object> clearTableOrders(UUID tableId);

}
