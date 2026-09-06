package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.WorkerDataProvider;
import dev.kinodesu.calculaconta.infra.repository.OrderRepository;
import dev.kinodesu.calculaconta.infra.repository.TableRepository;
import dev.kinodesu.calculaconta.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class WorkerDataProviderImpl implements WorkerDataProvider {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final TableRepository tableRepository;

    @Override
    public void clearOldData(LocalDateTime expirationDate) {
        log.info("limpando order");
        orderRepository.clearOldData(expirationDate);
        log.info("limpando user");
        clientRepository.clearOldData(expirationDate);
        log.info("limpando room");
        tableRepository.clearOldData(expirationDate);
    }

    @Override
    public void healthCheck() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = "http://localhost:8080/actuator/health";
            String response = restTemplate.getForObject(url, String.class);
            log.info(response);
        } catch (Exception e) {
            log.error("Erro na chamada agendada: " + e);
        }
    }
}
