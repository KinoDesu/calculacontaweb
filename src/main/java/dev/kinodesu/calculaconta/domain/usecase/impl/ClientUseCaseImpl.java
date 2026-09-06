package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.ClientDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Client;
import dev.kinodesu.calculaconta.domain.usecase.ClientUseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class ClientUseCaseImpl implements ClientUseCase {

    private final ClientDataProvider clientDataProvider;

    public ClientUseCaseImpl(ClientDataProvider clientDataProvider) {
        this.clientDataProvider = clientDataProvider;
    }

    @Override
    public Client saveClient(Client client) {
        log.info("save client");
        return clientDataProvider.saveClient(client);
    }

    @Override
    public Client getClientById(UUID clientId) {
        return clientDataProvider.getClientById(clientId);
    }

    @Override
    public List<Client> getAllClientsByTableId(UUID tableId) {
        return clientDataProvider.getAllClientsByTableId(tableId);
    }
}
