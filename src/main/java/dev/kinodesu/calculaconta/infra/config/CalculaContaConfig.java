package dev.kinodesu.calculaconta.infra.config;

import dev.kinodesu.calculaconta.domain.OrderDataProvider;
import dev.kinodesu.calculaconta.domain.TableDataProvider;
import dev.kinodesu.calculaconta.domain.ClientDataProvider;
import dev.kinodesu.calculaconta.domain.WorkerDataProvider;
import dev.kinodesu.calculaconta.domain.command.TableClientCommand;
import dev.kinodesu.calculaconta.domain.command.impl.TableClientCommandImpl;
import dev.kinodesu.calculaconta.domain.usecase.TableUseCase;
import dev.kinodesu.calculaconta.domain.usecase.ClientUseCase;
import dev.kinodesu.calculaconta.domain.usecase.WorkerUseCase;
import dev.kinodesu.calculaconta.domain.usecase.impl.TableUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.ClientUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.WorkerUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculaContaConfig {

    @Bean
    public ClientUseCase userUseCase(ClientDataProvider clientDataProvider) {
        return new ClientUseCaseImpl(clientDataProvider);
    }

    @Bean
    public TableUseCase roomUseCase(TableDataProvider tableDataProvider) {
        return new TableUseCaseImpl(tableDataProvider);
    }

    @Bean
    public WorkerUseCase workerUseCase(WorkerDataProvider workerDataProvider) {
        return new WorkerUseCaseImpl(workerDataProvider);
    }

    @Bean
    public TableClientCommand roomUserCommand(TableUseCase tableUseCase) {
        return new TableClientCommandImpl(tableUseCase);
    }
}
