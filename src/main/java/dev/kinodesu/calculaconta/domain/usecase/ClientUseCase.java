package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientUseCase {
    Client saveClient(Client client);

    Client getClientById(UUID clientId);

    List<Client> getAllClientsByTableId(UUID tableId);
}
