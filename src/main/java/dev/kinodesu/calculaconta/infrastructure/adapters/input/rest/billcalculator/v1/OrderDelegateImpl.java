package dev.kinodesu.calculaconta.infrastructure.adapters.input.rest.billcalculator.v1;

import dev.kinodesu.calculaconta.api.OrderApiDelegate;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;
import dev.kinodesu.calculaconta.application.usecases.OrderUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderDelegateImpl implements OrderApiDelegate {

    private final OrderUseCase orderUseCase;

    @Override
    public CompletableFuture<ResponseEntity<Void>> deleteOrder(UUID orderId) {
        return CompletableFuture.supplyAsync(()->
        {
            orderUseCase.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        });
    }

    @Override
    public CompletableFuture<ResponseEntity<OrderResponseDTO>> getOrderById(UUID orderId) {
        return CompletableFuture.supplyAsync(()-> ResponseEntity.ok(orderUseCase.getOrderById(orderId)));
    }
}
