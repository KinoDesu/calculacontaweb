package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.TableRequestDTO;
import dev.kinodesu.calculaconta.app.service.TableService;
import dev.kinodesu.calculaconta.domain.entity.Table;
import dev.kinodesu.calculaconta.domain.usecase.TableUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableUseCase tableUseCase;

    @Override
    public Table createTable(TableRequestDTO tableRequestDTO) {
        Table table = Table.builder()
                .tableId(tableRequestDTO.getTableId())
                .name(tableRequestDTO.getTableName())
                .clientQuantity(tableRequestDTO.getClientQuantity())
                .build();

        return tableUseCase.createTable(table, tableRequestDTO.getRedirectUrl());
    }

    @Override
    public byte[] requestQrCode(UUID tableId) {
        return tableUseCase.requestQrCode(tableId);
    }

    @Override
    public Table findByCode(String tableCode) {
        return tableUseCase.findByCode(tableCode);
    }
}
