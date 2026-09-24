package dev.kinodesu.calculaconta.application.ports.input;

import dev.kinodesu.calculaconta.application.ports.input.mapper.OrderMapper;
import dev.kinodesu.calculaconta.application.ports.output.ClientOrderOutputPort;
import dev.kinodesu.calculaconta.application.ports.output.OrderOutputPort;
import dev.kinodesu.calculaconta.application.usecases.OrderUseCase;
import dev.kinodesu.calculaconta.domain.entity.OrderRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.OrderResponseDTO;
import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderInputPort implements OrderUseCase {

    private final OrderOutputPort orderOutputPort;
    private final ClientOrderOutputPort clientOrderOutputPort;
    private final OrderMapper orderMapper;
    private final OrderService orderService;

    @Override
    public void deleteOrder(UUID orderId) {
        orderOutputPort.deleteOrder(orderId);
    }

    @Override
    public OrderResponseDTO getOrderById(UUID orderId) {

        Order order = orderOutputPort.findById(orderId);

        return orderMapper.toResponse(order);
    }

    @Override
    public OrderResponseDTO createOrUpdateTableOrder(UUID tableId, OrderRequestDTO orderRequestDTO) {

        orderService.validateOrder(orderRequestDTO);

        BigDecimal totalAmount = orderService.calulateTotalAmount(orderRequestDTO.getUnitPrice(), orderRequestDTO.getQuantity());
        BigDecimal pricePerPerson = orderService.calulatePricePerPerson(orderRequestDTO.getUnitPrice(), orderRequestDTO.getQuantity(), orderRequestDTO.getClientList().size());
        Order newOrder = orderMapper.toEntity(orderRequestDTO, tableId, totalAmount, pricePerPerson);

        return orderMapper.toResponse(orderOutputPort.createOrUpdateTableOrder(newOrder));
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersByTableId(UUID tableId) {
        List<Order> orderList = orderOutputPort.findAllByTableId(tableId);
        return orderList.stream().map(orderMapper::toResponse).toList();
    }
}
