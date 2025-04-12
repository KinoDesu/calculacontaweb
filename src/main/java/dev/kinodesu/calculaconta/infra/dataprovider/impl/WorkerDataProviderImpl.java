package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.infra.dataprovider.WorkerDataProvider;
import dev.kinodesu.calculaconta.infra.repository.OrderRepository;
import dev.kinodesu.calculaconta.infra.repository.RoomRepository;
import dev.kinodesu.calculaconta.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class WorkerDataProviderImpl implements WorkerDataProvider {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    public void clearOldData(LocalDateTime expirationDate) {
        log.info("limpando order");
        orderRepository.clearOldData(expirationDate);
        log.info("limpando user");
        userRepository.clearOldData(expirationDate);
        log.info("limpando room");
        roomRepository.clearOldData(expirationDate);
    }
}
