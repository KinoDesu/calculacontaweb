package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.app.dto.request.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientService {
    Client saveClient(ClientRequestDTO clientRequestDTO);

    List<Client> getAllClientsByTableId(UUID tableId);

    Client getClientById(UUID clientId);
}
