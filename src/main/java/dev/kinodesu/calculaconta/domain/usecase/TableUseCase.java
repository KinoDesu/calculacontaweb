package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.Table;

import java.util.UUID;

public interface TableUseCase {
    Table createTable(Table table, String redirectUrl);

    Table findByCode(String tableCode);

    byte[] requestQrCode(UUID tableId);

    Table findById(UUID tableId);
}
