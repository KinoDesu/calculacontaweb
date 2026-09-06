package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.TableDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Table;
import dev.kinodesu.calculaconta.domain.usecase.TableUseCase;

import java.util.UUID;

public class TableUseCaseImpl implements TableUseCase {

    private final TableDataProvider tableDataProvider;

    public TableUseCaseImpl(TableDataProvider tableDataProvider) {
        this.tableDataProvider = tableDataProvider;
    }

    @Override
    public Table createTable(Table table, String redirectUrl) {
        return tableDataProvider.create(table, redirectUrl);
    }

    @Override
    public Table findByCode(String tableCode) {
        if(tableCode==null){
            return null;
        }
        return tableDataProvider.findByCode(tableCode);
    }

    @Override
    public byte[] requestQrCode(UUID tableId) {
        return tableDataProvider.getQrCodeByTableId(tableId);
    }

    @Override
    public Table findById(UUID tableId) {
        return tableDataProvider.findByid(tableId);
    }

}
