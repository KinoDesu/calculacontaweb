package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa;

import dev.kinodesu.calculaconta.domain.model.Order;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper.OrderDataMapper;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.ClientRepository;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.OrderRepository;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientOrderData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderClientId;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderData;
import dev.kinodesu.calculaconta.application.ports.output.OrderOutputPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderOutputPort {

    private final OrderRepository orderRepository;
    private final OrderDataMapper orderDataMapper;
    private final ClientRepository clientRepository;

    @Override
    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderDataMapper::toEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Pedido %s não encontrado", orderId)));
    }

    @Override
    public Order createOrUpdateTableOrder(Order order) {
        if(order.getOrderId()!=null){
            OrderData existingOrder = orderRepository.findById(order.getOrderId()).orElseThrow();
            orderDataMapper.updateData(order, existingOrder);

            return orderDataMapper.toEntity(orderRepository.save(existingOrder));
        }

        OrderData orderData = orderDataMapper.toData(order);
        orderData.setOrderId(UUID.randomUUID());

        List<ClientOrderData> clientOrderDataList =
                order.getClientIdList()
                        .stream()
                        .map(clientId -> {

                            ClientData clientData = clientRepository
                                    .findById(clientId)
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
                        .toList();

        orderData.setClientOrderDataList(clientOrderDataList);

        return orderDataMapper.toEntity(
                orderRepository.save(orderData)
        );
    }

    @Override
    public List<Order> findAllByTableId(UUID tableId) {
        return orderDataMapper.toEntity(orderRepository.findAllByTableId(tableId));
    }
}
