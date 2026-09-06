package dev.kinodesu.calculaconta.infra.config;

import dev.kinodesu.calculaconta.domain.OrderDataProvider;
import dev.kinodesu.calculaconta.domain.TableDataProvider;
import dev.kinodesu.calculaconta.domain.ClientDataProvider;
import dev.kinodesu.calculaconta.domain.WorkerDataProvider;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import dev.kinodesu.calculaconta.domain.usecase.TableUseCase;
import dev.kinodesu.calculaconta.domain.usecase.ClientUseCase;
import dev.kinodesu.calculaconta.domain.usecase.WorkerUseCase;
import dev.kinodesu.calculaconta.domain.usecase.impl.OrderUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.TableUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.ClientUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.WorkerUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculaContaConfig {

    @Bean
    public ClientUseCase clientUseCase(ClientDataProvider clientDataProvider) {
        return new ClientUseCaseImpl(clientDataProvider);
    }

    @Bean
    public TableUseCase tableUseCase(TableDataProvider tableDataProvider) {
        return new TableUseCaseImpl(tableDataProvider);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderDataProvider orderDataProvider){
        return new OrderUseCaseImpl(orderDataProvider);
    }

    @Bean
    public WorkerUseCase workerUseCase(WorkerDataProvider workerDataProvider) {
        return new WorkerUseCaseImpl(workerDataProvider);
    }
}
