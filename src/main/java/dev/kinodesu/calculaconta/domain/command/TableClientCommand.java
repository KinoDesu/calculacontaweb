package dev.kinodesu.calculaconta.domain.command;

import dev.kinodesu.calculaconta.domain.entity.Table;

import java.util.UUID;

public interface TableClientCommand {

    Table getTableById(UUID tableId);
}
