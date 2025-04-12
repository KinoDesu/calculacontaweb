package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.command.BillingCommand;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.BillingUseCase;

import java.util.List;

public class BillingUseCaseImpl implements BillingUseCase {

    private final BillingCommand billingCommand;

    public BillingUseCaseImpl(BillingCommand billingCommand) {
        this.billingCommand = billingCommand;
    }

    @Override
    public double getSubtotal(String roomCode) {
        List<User> userList = billingCommand.getAllUsersByRoom(roomCode);

        double subtotal = userList.stream()
                .mapToDouble(User::getTotalAmount)
                .sum();

        return Double.parseDouble(String.format("%.2f", subtotal));
    }

    @Override
    public double getTotal(String roomCode, int service) {

        double subtotal = getSubtotal(roomCode);
        double total = subtotal * (1 + (double) service / 100);

        return Double.parseDouble(String.format("%.2f", total));
    }

    @Override
    public double calculateBillingService(String user, int service) {
        double userTotalAmount = billingCommand.getUserById(user).getTotalAmount();
        double calculatedValue = userTotalAmount * (1 + (double) service / 100);
        return Double.parseDouble(String.format("%.2f", calculatedValue));
    }
}
