package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.OrderController;
import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/order")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveNewOrder(@RequestBody OrderRequestDTO order) {
        orderService.saveNewOrder(order);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
