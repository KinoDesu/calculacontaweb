package dev.kinodesu.calculaconta.infrastructure.adapters.input.rest.billcalculator.v1;

import dev.kinodesu.calculaconta.api.ClientApiDelegate;
import dev.kinodesu.calculaconta.application.usecases.ClientUseCase;
import dev.kinodesu.calculaconta.domain.entity.ClientResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientDelegateImpl implements ClientApiDelegate {

    private final ClientUseCase clientUseCase;

    @Override
    public CompletableFuture<ResponseEntity<ClientResponseDTO>> getClientById(UUID clientId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(clientUseCase.getClientById(clientId)));
    }
}
