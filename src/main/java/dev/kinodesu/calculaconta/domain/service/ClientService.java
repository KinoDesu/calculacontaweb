package dev.kinodesu.calculaconta.domain.service;

import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;

public interface ClientService {
    void validateClient(ClientRequestDTO client);
}
