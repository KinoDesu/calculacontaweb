package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.Client;

import java.util.List;
import java.util.UUID;

public interface ClientDataProvider {
    Client saveClient(Client client);

    Client getClientById(UUID id);

    List<Client> getAllClientsByTableId(UUID tableId);

}
