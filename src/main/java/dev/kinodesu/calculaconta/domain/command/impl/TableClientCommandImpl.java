package dev.kinodesu.calculaconta.domain.command.impl;

import dev.kinodesu.calculaconta.domain.command.TableClientCommand;
import dev.kinodesu.calculaconta.domain.entity.Table;
import dev.kinodesu.calculaconta.domain.usecase.TableUseCase;

import java.util.UUID;

public class TableClientCommandImpl implements TableClientCommand {

    private final TableUseCase tableUseCase;

    public TableClientCommandImpl(TableUseCase tableUseCase) {
        this.tableUseCase = tableUseCase;
    }

    @Override
    public Table getTableById(UUID tableId) {
        return tableUseCase.findById(tableId);
    }
}
