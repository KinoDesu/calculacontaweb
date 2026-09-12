package dev.kinodesu.calculaconta.infrastructure.adapters.input.rest.billcalculator.v1;

import dev.kinodesu.calculaconta.api.TableApiDelegate;
import dev.kinodesu.calculaconta.application.usecases.ClientUseCase;
import dev.kinodesu.calculaconta.application.usecases.OrderUseCase;
import dev.kinodesu.calculaconta.application.usecases.TableUseCase;
import dev.kinodesu.calculaconta.domain.entity.ClientRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.ClientResponseDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;
import dev.kinodesu.calculaconta.domain.entity.TableQrCodeResponseDTO;
import dev.kinodesu.calculaconta.domain.entity.TableRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.TableResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class TableDelegateImpl implements TableApiDelegate {

    private final TableUseCase tableUseCase;
    private final ClientUseCase clientUseCase;
    private final OrderUseCase orderUseCase;

    @Override
    public CompletableFuture<ResponseEntity<Void>> clearTableOrders(UUID tableId) {
        return CompletableFuture.supplyAsync(() -> {
            tableUseCase.clearTableOrders(tableId);
            return ResponseEntity.noContent().build();
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<TableResponseDTO>> createOrUpdateTable(TableRequestDTO tableRequestDTO) {

        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentContextPath();

        return CompletableFuture.supplyAsync(() -> {
            TableResponseDTO savedTable = tableUseCase.createOrUpdateTable(tableRequestDTO);

            URI location = servletUriComponentsBuilder
                    .path("/table/{tableId}")
                    .buildAndExpand(savedTable.getTableId())
                    .toUri();

            return ResponseEntity.created(location).build();
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<ClientResponseDTO>> createOrUpdateTableClient(UUID tableId, ClientRequestDTO clientRequestDTO) {

        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentContextPath();

        return CompletableFuture.supplyAsync(() -> {
            ClientResponseDTO savedClient = clientUseCase.createOrUpdateTableClient(tableId, clientRequestDTO);

            URI location = servletUriComponentsBuilder
                    .path("/client/{clientId}")
                    .buildAndExpand(savedClient.getClientId())
                    .toUri();

            return ResponseEntity.created(location).build();
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<OrderResponseDTO>> createOrUpdateTableOrder(UUID tableId, OrderRequestDTO orderRequestDTO) {

        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentContextPath();

        return CompletableFuture.supplyAsync(() -> {
            OrderResponseDTO savedOrder = orderUseCase.createOrUpdateTableOrder(tableId, orderRequestDTO);

            URI location = servletUriComponentsBuilder
                    .path("/order/{orderId}")
                    .buildAndExpand(savedOrder.getOrderId())
                    .toUri();

            return ResponseEntity.created(location).build();
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<TableResponseDTO>> getTableById(UUID tableId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(tableUseCase.getTableById(tableId)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<ClientResponseDTO>>> getTableClients(UUID tableId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(clientUseCase.getAllClientsByTableId(tableId)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<OrderResponseDTO>>> getTableOrders(UUID tableId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(orderUseCase.getAllOrdersByTableId(tableId)));
    }

    @Override
    public CompletableFuture<ResponseEntity<TableQrCodeResponseDTO>> getTableQrCode(UUID tableId) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(tableUseCase.getTableQrCode(tableId)));
    }

    @Override
    public CompletableFuture<ResponseEntity<TableResponseDTO>> getTableByCode(String tableCode) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(tableUseCase.getTableByCode(tableCode)));
    }
}
