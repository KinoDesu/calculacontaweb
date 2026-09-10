package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa;

import dev.kinodesu.calculaconta.domain.model.ClientOrder;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.ClientOrderRepository;
import dev.kinodesu.calculaconta.application.ports.output.ClientOrderOutputPort;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper.ClientOrderDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClientOrderAdapterImpl implements ClientOrderOutputPort {

    private final ClientOrderRepository clientOrderRepository;
    private final ClientOrderDataMapper clientOrderDataMapper;

    @Override
    public List<ClientOrder> findAllByOrderId(UUID orderId) {
        return clientOrderRepository.findAllByOrderClientIdOrderId(orderId)
                .stream()
                .map(clientOrderDataMapper::toEntity)
                .toList();
    }
}
