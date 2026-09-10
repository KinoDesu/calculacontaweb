package dev.kinodesu.calculaconta.application.usecases;

import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.ClientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClientUseCase {
    ClientResponseDTO getClientById(UUID clientId);

    ClientResponseDTO createOrUpdateTableClient(UUID tableId, ClientRequestDTO clientRequestDTO);

    List<ClientResponseDTO> getAllClientsByTableId(UUID tableId);
}
