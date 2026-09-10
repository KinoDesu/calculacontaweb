package dev.kinodesu.calculaconta.domain.service.impl;

import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.exception.InvalidValueException;
import dev.kinodesu.calculaconta.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Override
    public BigDecimal calulateTotalAmount(Double unitPrice, int quantity) {
        return BigDecimal.valueOf(unitPrice * quantity);
    }

    @Override
    public BigDecimal calulatePricePerPerson(Double unitPrice, int quantity, int clientQuantity) {
        BigDecimal totalAmount = calulateTotalAmount(unitPrice, quantity);
        return totalAmount.divide(BigDecimal.valueOf(clientQuantity), 2, RoundingMode.HALF_UP);
    }

    @Override
    public void validateOrder(OrderRequestDTO order) {
        if (order.getQuantity() == null || order.getQuantity() <= 0) {
            throw new InvalidValueException("Valor inválido para quantidade de itens");
        }

        if(order.getUnitPrice() == null || BigDecimal.ZERO.compareTo(BigDecimal.valueOf(order.getUnitPrice())) >= 0){
            throw new InvalidValueException("Valor inválido para pedido. Valor deve ser maior que 0 (zero)");
        }

        if(order.getName() == null || order.getName().isBlank()){
            throw new InvalidValueException("Nome do item inválido");
        }
    }
}
