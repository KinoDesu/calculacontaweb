package dev.kinodesu.calculaconta.application.ports.output;

import dev.kinodesu.calculaconta.domain.model.Client;

import java.util.List;
import java.util.UUID;

public interface ClientOutputPort {
    Client findClientById(UUID clientId);

    Client createOrUpdateTableClient(Client client);

    List<Client> findAllClientsByTableId(UUID tableId);
}
