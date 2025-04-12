package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.BillingController;
import dev.kinodesu.calculaconta.app.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/billing")
public class BillingControllerImpl implements BillingController {

    private final BillingService billingService;

    @Override
    @GetMapping("/subtotal/{roomCode}")
    public ResponseEntity<Object> getSubtotal(@PathVariable String roomCode) {
        return ResponseEntity.ok(billingService.getSubtotal(roomCode));
    }

    @Override
    @GetMapping(value = "/total/{roomCode}", params = {"service"})
    public ResponseEntity<Object> getTotal(@PathVariable String roomCode, @RequestParam int service) {
        return ResponseEntity.ok(billingService.getTotal(roomCode, service));
    }

    @Override
    @GetMapping(value = "/calculate/{user}", params = {"service"})
    public ResponseEntity<Object> calculateBillingService(@PathVariable String user, @RequestParam int service) {
        return ResponseEntity.ok(billingService.calculateBillingService(user, service));
    }
}
