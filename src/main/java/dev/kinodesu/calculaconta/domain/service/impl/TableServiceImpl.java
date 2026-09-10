package dev.kinodesu.calculaconta.domain.service.impl;

import dev.kinodesu.calculaconta.domain.exception.InvalidValueException;
import dev.kinodesu.calculaconta.domain.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    @Override
    public void validateTableClientQuantity(Integer quantity) {
        if (quantity == null || quantity <= 1) {
            throw new InvalidValueException("Valor inválido para quantidade de clientes na mesa");
        }
    }
}
