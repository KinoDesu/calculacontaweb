package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa;

import dev.kinodesu.calculaconta.application.ports.output.ClientOrderOutputPort;
import dev.kinodesu.calculaconta.application.ports.output.OrderOutputPort;
import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientOrderData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderClientId;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper.OrderDataMapper;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.ClientRepository;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderDataMapper orderDataMapper;
    private final ClientRepository clientRepository;
    private final ClientOrderOutputPort clientOrderAdapter;

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(UUID orderId) {
        return orderRepository.findByIdWithClients(orderId)
                .map(orderDataMapper::toEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Pedido %s não encontrado", orderId)));
    }

    @Override
    public Order createOrUpdateTableOrder(Order order) {
        if (order.getOrderId() != null) {
            clientOrderAdapter.deleteAllByOrderId(order.getOrderId());
            OrderData existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow();
            existingOrder.setClientOrderDataList(createClientOrderList(order, existingOrder));
            orderDataMapper.updateData(order, existingOrder);

            return orderDataMapper.toEntity(orderRepository.save(existingOrder));
        }

        OrderData orderData = orderDataMapper.toData(order);
        orderData.setOrderId(UUID.randomUUID());
        orderData.setClientOrderDataList(createClientOrderList(order, orderData));

        Order a = orderDataMapper.toEntity(
                orderRepository.save(orderData)
        );
        return a;
    }

    private List<ClientOrderData> createClientOrderList(Order order, OrderData orderData) {
        Set<ClientOrderData> clientOrderDataList =
                order.getClientOrderList()
                        .stream()
                        .map(client -> {

                            ClientData clientData = clientRepository
                                    .findById(client.getClientId())
                                    .orElseThrow();

                            return ClientOrderData.builder()
                                    .orderClientId(new OrderClientId(
                                            clientData.getClientId(),
                                            orderData.getOrderId()
                                    ))
                                    .orderData(orderData)
                                    .clientData(clientData)
                                    .value(order.getPricePerPerson())
                                    .build();

                        })
                        .collect(Collectors.toSet());
        return clientOrderDataList.stream().toList();
    }

    @Override
    public List<Order> findAllByTableId(UUID tableId) {
        return orderDataMapper.toEntity(orderRepository.findAllByTableId(tableId));
    }
}
