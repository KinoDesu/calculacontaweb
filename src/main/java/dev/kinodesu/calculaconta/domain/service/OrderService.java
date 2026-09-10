package dev.kinodesu.calculaconta.domain.service;

import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;

import java.math.BigDecimal;

public interface OrderService {
    BigDecimal calulateTotalAmount(Double unitPrice, int quantity);
    BigDecimal calulatePricePerPerson(Double unitPrice, int quantity, int clientQuantity);
    void validateOrder(OrderRequestDTO order);
}
