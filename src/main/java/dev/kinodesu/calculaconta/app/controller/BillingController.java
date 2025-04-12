package dev.kinodesu.calculaconta.app.controller;

import org.springframework.http.ResponseEntity;

public interface BillingController {

    ResponseEntity<Object> getSubtotal(String roomCode);
    ResponseEntity<Object> getTotal(String roomCode, int service);
    ResponseEntity<Object> calculateBillingService(String user, int service);
}
