package dev.kinodesu.calculaconta.app.controller;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import org.springframework.http.ResponseEntity;

public interface OrderController {
    ResponseEntity<Object> saveNewOrder(OrderRequestDTO order);
    ResponseEntity<Object> getAllOrders();
}
