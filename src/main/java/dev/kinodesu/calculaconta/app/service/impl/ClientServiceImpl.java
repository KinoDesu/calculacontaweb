package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.ClientRequestDTO;
import dev.kinodesu.calculaconta.app.service.ClientService;
import dev.kinodesu.calculaconta.domain.entity.Client;
import dev.kinodesu.calculaconta.domain.usecase.ClientUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientUseCase clientUseCase;

    @Override
    public Client saveClient(ClientRequestDTO clientRequestDTO) {

        Client client = Client.builder()
                .clientId(clientRequestDTO.getClientId())
                .name(clientRequestDTO.getName())
                .isBot(clientRequestDTO.isBot())
                .tableId(clientRequestDTO.getTableId())
                .build();

        return clientUseCase.saveClient(client);
    }

    @Override
    public List<Client> getAllClientsByTableId(UUID tableId) {
        return clientUseCase.getAllClientsByTableId(tableId);
    }

    @Override
    public Client getClientById(UUID clientId) {
        return clientUseCase.getClientById(clientId);
    }
}
