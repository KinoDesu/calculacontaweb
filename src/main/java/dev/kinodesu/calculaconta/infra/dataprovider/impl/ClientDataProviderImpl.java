package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.ClientDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Client;
import dev.kinodesu.calculaconta.infra.mapper.ClientDataMapper;
import dev.kinodesu.calculaconta.infra.repository.ClientRepository;
import dev.kinodesu.calculaconta.infra.repository.data.ClientData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientDataProviderImpl implements ClientDataProvider {

    private final ClientRepository clientRepository;
    private final ClientDataMapper clientDataMapper;

    @Override
    public Client saveClient(Client client) {

        if (client.getClientId() != null) {
            ClientData existingClient = clientRepository.findById(client.getClientId()).orElseThrow();
            clientDataMapper.updateData(client, existingClient);

            return clientDataMapper.toEntity(clientRepository.save(existingClient));
        }
        ClientData clientData = clientDataMapper.toData(client);
        clientData.setClientId(UUID.randomUUID());
        return clientDataMapper.toEntity(clientRepository.save(clientData));
    }

    @Override
    public Client getClientById(UUID id) {
        ClientData clientData = clientRepository.findById(id).orElseThrow();
        return clientDataMapper.toEntity(clientData);
    }

    @Override
    public List<Client> getAllClientsByTableId(UUID tableId) {
        return clientDataMapper.toEntity(clientRepository.findAllByTableId(tableId));
    }
}
