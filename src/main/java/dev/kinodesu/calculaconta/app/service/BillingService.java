package dev.kinodesu.calculaconta.app.service;

import org.springframework.http.ResponseEntity;

public interface BillingService {
    double getSubtotal(String roomCode);
    double getTotal(String roomCode, int service);
    double calculateBillingService(String user, int service);
}
