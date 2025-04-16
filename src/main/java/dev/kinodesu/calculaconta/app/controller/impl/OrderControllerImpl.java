package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.OrderController;
import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.app.service.OrderService;
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

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveNewOrder(@RequestBody OrderRequestDTO order) {
        orderService.saveNewOrder(order);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    @GetMapping("/{roomCode}")
    public ResponseEntity<Object> getAllOrdersByRoomCode(@PathVariable String roomCode) {
        return ResponseEntity.ok(orderService.getAllOrdersByRoomCode(roomCode));
    }

    @Override
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable String orderId) {

        orderService.deleteOrder(orderId);

        return ResponseEntity.noContent().build();
    }
}
