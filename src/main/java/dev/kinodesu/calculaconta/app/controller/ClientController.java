package dev.kinodesu.calculaconta.app.controller;

import dev.kinodesu.calculaconta.app.dto.request.ClientRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ClientController {

    ResponseEntity<Object> saveClient(ClientRequestDTO user);

    ResponseEntity<Object> getClientById(UUID clientId);

    ResponseEntity<Object> getAllClientsByTableId(UUID tableId);
}
