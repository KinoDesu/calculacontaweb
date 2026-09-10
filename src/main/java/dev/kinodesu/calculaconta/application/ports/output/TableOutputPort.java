package dev.kinodesu.calculaconta.application.ports.output;

import dev.kinodesu.calculaconta.domain.model.Table;

import java.net.URI;
import java.util.UUID;

public interface TableOutputPort {
    void deleteAllOrdersByTableId(UUID tableId);

    Table createOrUpdateTable(Table table, URI redirectUrl);

    Table getTableById(UUID tableId);
}
