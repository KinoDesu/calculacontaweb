package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa;

import dev.kinodesu.calculaconta.application.ports.output.ClientOutputPort;
import dev.kinodesu.calculaconta.domain.model.Client;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper.ClientDataMapper;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientAdapterImpl implements ClientOutputPort {

    private final ClientRepository clientRepository;
    private final ClientDataMapper clientDataMapper;

    @Override
    public Client findClientById(UUID clientId) {
        return clientRepository.findById(clientId)
                .map(clientDataMapper::toEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cliente %s não encontrado", clientId)));
    }

    @Override
    public Client createOrUpdateTableClient(Client client) {
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
    public List<Client> findAllClientsByTableId(UUID tableId) {
        return clientDataMapper.toEntity(clientRepository.findAllByTableId(tableId));
    }
}
