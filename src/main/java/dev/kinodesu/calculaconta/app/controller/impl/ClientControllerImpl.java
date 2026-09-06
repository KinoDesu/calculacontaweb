package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.ClientController;
import dev.kinodesu.calculaconta.app.dto.request.ClientRequestDTO;
import dev.kinodesu.calculaconta.app.service.ClientService;
import dev.kinodesu.calculaconta.domain.entity.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/client")
public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody ClientRequestDTO clientRequestDTO) {
            Client savedClient = clientService.saveClient(clientRequestDTO);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{clientId}")
                    .buildAndExpand(savedClient.getClientId())
                    .toUri();

            return ResponseEntity.created(location).build();
    }

    @Override
    @GetMapping("/{clientId}")
    public ResponseEntity<Object> getClientById(@PathVariable UUID clientId) {
        try {
            Client client = clientService.getClientById(clientId);
            return ResponseEntity.ok(client);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping(params = {"roomCode"})
    public ResponseEntity<Object> getAllClientsByTableId(@RequestParam UUID tableId) {
        return ResponseEntity.ok(clientService.getAllClientsByTableId(tableId));
    }
}
