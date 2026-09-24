package dev.kinodesu.calculaconta.application.ports.input;

import dev.kinodesu.calculaconta.application.ports.input.mapper.ClientMapper;
import dev.kinodesu.calculaconta.application.ports.output.ClientOutputPort;
import dev.kinodesu.calculaconta.application.usecases.ClientUseCase;
import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.ClientResponseDTO;
import dev.kinodesu.calculaconta.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientInputPort implements ClientUseCase {

    private final ClientOutputPort clientOutputPort;
    private final ClientMapper clientMapper;
    private final ClientService clientService;

    @Override
    public ClientResponseDTO getClientById(UUID clientId) {
        return clientMapper.toResponse(clientOutputPort.findClientById(clientId));
    }

    @Override
    public ClientResponseDTO createOrUpdateTableClient(UUID tableId, ClientRequestDTO clientRequestDTO) {
        clientService.validateClient(clientRequestDTO);
        return clientMapper.toResponse(clientOutputPort.createOrUpdateTableClient(clientMapper.toEntity(clientRequestDTO, tableId)));
    }

    @Override
    public List<ClientResponseDTO> getAllClientsByTableId(UUID tableId) {
        return clientMapper.toResponse(clientOutputPort.findAllClientsByTableId(tableId));
    }
}
