package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.app.dto.request.TableRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.Table;

import java.util.UUID;

public interface TableService {

    Table createTable(TableRequestDTO tableRequestDTO);

    byte[] requestQrCode(UUID tableId);

    Table findByCode(String tableCode);
}
