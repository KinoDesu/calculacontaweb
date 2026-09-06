package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.Table;

import java.util.UUID;

public interface TableDataProvider {
    Table create(Table table, String redirectUrl);

    Table findByCode(String tableCode);

    byte[] generateQrCode(String redirectUrl);

    byte[] getQrCodeByTableId(UUID tableId);

    Table findByid(UUID tableId);
}
