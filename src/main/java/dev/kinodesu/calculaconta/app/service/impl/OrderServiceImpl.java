package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.OrderRequestDTO;
import dev.kinodesu.calculaconta.app.service.OrderService;
import dev.kinodesu.calculaconta.domain.entity.Client;
import dev.kinodesu.calculaconta.domain.entity.Order;
import dev.kinodesu.calculaconta.domain.usecase.ClientUseCase;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderUseCase orderUseCase;
    private final ClientUseCase clientUseCase;

    @Override
    public Order saveOrder(OrderRequestDTO orderRequestDTO) {

        double totalPrice = orderRequestDTO.getUnitPrice() * orderRequestDTO.getQuantity();
        List<Client> clientList = clientUseCase.getAllClientsByTableId(orderRequestDTO.getTableId()).stream()
                .filter(
                        client -> orderRequestDTO.getClientList()
                                .contains(client.getClientId())
                )
                .toList();

        Order newOrder = Order.builder()
                .orderId(orderRequestDTO.getOrderId())
                .name(orderRequestDTO.getName())
                .unitPrice(orderRequestDTO.getUnitPrice())
                .quantity(orderRequestDTO.getQuantity())
                .totalPrice(totalPrice)
                .pricePerPerson(totalPrice / orderRequestDTO.getClientList().size())
                .clientList(clientList)
                .tableId(orderRequestDTO.getTableId())
                .build();

        return orderUseCase.saveOrder(newOrder);
    }

    @Override
    public List<Order> listTableOrders(UUID tableId) {
        return orderUseCase.listTableOrders(tableId);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        orderUseCase.deleteOrder(orderId);
    }

    @Override
    public void clearTableOrders(UUID tableId) {
        orderUseCase.clearTableOrders(tableId);
    }
}
