package dev.kinodesu.calculaconta.domain.usecase;

public interface BillingUseCase {
    double getSubtotal(String roomCode);

    double getTotal(String roomCode, int service);

    double calculateBillingService(String user, int service);
}
