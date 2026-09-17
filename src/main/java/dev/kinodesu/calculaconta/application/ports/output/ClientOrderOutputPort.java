package dev.kinodesu.calculaconta.application.ports.output;

import dev.kinodesu.calculaconta.domain.model.ClientOrder;

import java.util.List;
import java.util.UUID;

public interface ClientOrderOutputPort {
    List<ClientOrder> findAllByOrderId(UUID orderId);
}
