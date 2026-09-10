package dev.kinodesu.calculaconta.application.ports.input;

import dev.kinodesu.calculaconta.domain.entity.TableQrCodeResponseDTO;
import dev.kinodesu.calculaconta.domain.entity.TableRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.TableResponseDTO;
import dev.kinodesu.calculaconta.application.ports.input.mapper.TableMapper;
import dev.kinodesu.calculaconta.application.ports.input.mapper.TableQrCodeMapper;
import dev.kinodesu.calculaconta.application.ports.output.TableOutputPort;
import dev.kinodesu.calculaconta.application.usecases.TableUseCase;
import dev.kinodesu.calculaconta.domain.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableInputPort implements TableUseCase {

    private final TableOutputPort tableOutputPort;
    private final TableMapper tableMapper;
    private final TableQrCodeMapper tableQrCodeMapper;
    private final TableService tableService;

    @Override
    public void clearTableOrders(UUID tableId) {
        tableOutputPort.deleteAllOrdersByTableId(tableId);
    }

    @Override
    public TableResponseDTO createOrUpdateTable(TableRequestDTO tableRequestDTO) {
        tableService.validateTableClientQuantity(tableRequestDTO.getClientQuantity());

        return tableMapper.toResponse(
                tableOutputPort.createOrUpdateTable(tableMapper.toEntity(tableRequestDTO), tableRequestDTO.getRedirectUrl())
        );
    }

    @Override
    public TableResponseDTO getTableById(UUID tableId) {
        return tableMapper.toResponse(tableOutputPort.getTableById(tableId));
    }

    @Override
    public TableQrCodeResponseDTO getTableQrCode(UUID tableId) {
        return tableQrCodeMapper.toResponse(tableOutputPort.getTableById(tableId));
    }
}
