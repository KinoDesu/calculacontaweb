package dev.kinodesu.calculaconta.application.usecases;

import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {
    void deleteOrder(UUID orderId);

    OrderResponseDTO getOrderById(UUID orderId);

    OrderResponseDTO createOrUpdateTableOrder(UUID tableId, OrderRequestDTO orderRequestDTO);

    List<OrderResponseDTO> getAllOrdersByTableId(UUID tableId);
}
