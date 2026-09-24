package dev.kinodesu.calculaconta.application.usecases;

import dev.kinodesu.calculaconta.domain.entity.TableQrCodeResponseDTO;
import dev.kinodesu.calculaconta.domain.entity.TableRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.TableResponseDTO;

import java.util.UUID;

public interface TableUseCase {
    void clearTableOrders(UUID tableId);

    TableResponseDTO createOrUpdateTable(TableRequestDTO tableRequestDTO);

    TableResponseDTO getTableById(UUID tableId);

    TableQrCodeResponseDTO getTableQrCode(UUID tableId);

    TableResponseDTO getTableByCode(String tableCode);
}
