package dev.kinodesu.calculaconta.app.controller;

import dev.kinodesu.calculaconta.app.dto.request.TableRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TableController {

    ResponseEntity<Object> createTable(TableRequestDTO tableRequestDTO);
    ResponseEntity<Object> requestQrCode(UUID tableId);
}
