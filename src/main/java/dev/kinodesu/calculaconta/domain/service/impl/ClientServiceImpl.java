package dev.kinodesu.calculaconta.domain.service.impl;

import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.exception.InvalidValueException;
import dev.kinodesu.calculaconta.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Override
    public void validateClient(ClientRequestDTO client) {
        if (client.getName() == null || client.getName().isBlank()) {
            throw new InvalidValueException("Nome de cliente inválido");
        }
    }
}
