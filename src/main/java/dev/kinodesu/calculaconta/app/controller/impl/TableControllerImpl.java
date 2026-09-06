package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.TableController;
import dev.kinodesu.calculaconta.app.dto.request.TableRequestDTO;
import dev.kinodesu.calculaconta.app.service.TableService;
import dev.kinodesu.calculaconta.domain.entity.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/table")
public class TableControllerImpl implements TableController {

    private final TableService tableService;

    @Override
    @PostMapping
    public ResponseEntity<Object> createTable(@RequestBody TableRequestDTO tableRequestDTO) {
        Table savedTable = tableService.createTable(tableRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{tableId}")
                .buildAndExpand(savedTable.getTableId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping("/{tableId}/qrCode")
    public ResponseEntity<Object> requestQrCode(@PathVariable UUID tableId) {

        byte[] qrCodePngBytes = tableService.requestQrCode(tableId);

        return ResponseEntity.ok(qrCodePngBytes);
    }
}
