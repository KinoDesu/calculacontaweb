package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.OrderController;
import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.app.service.OrderService;
import dev.kinodesu.calculaconta.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order savedOrder = orderService.saveOrder(orderRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{orderId}")
                .buildAndExpand(savedOrder.getOrderId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping("/{tableId}")
    public ResponseEntity<Object> listTableOrders(@PathVariable UUID tableId) {
        return ResponseEntity.ok(orderService.listTableOrders(tableId));
    }

    @Override
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable UUID orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/clear/{tableId}")
    public ResponseEntity<Object> clearTableOrders(@PathVariable UUID tableId) {
        orderService.clearTableOrders(tableId);
        return ResponseEntity.noContent().build();
    }
}
