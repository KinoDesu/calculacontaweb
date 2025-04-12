package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.service.BillingService;
import dev.kinodesu.calculaconta.domain.usecase.BillingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillingUseCase billingUseCase;

    @Override
    public double getSubtotal(String roomCode) {
        return billingUseCase.getSubtotal(roomCode);
    }

    @Override
    public double getTotal(String roomCode, int service) {
        return billingUseCase.getTotal(roomCode, service);
    }

    @Override
    public double calculateBillingService(String user, int service) {
        return billingUseCase.calculateBillingService(user, service);
    }
}
