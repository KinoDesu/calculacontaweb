package dev.kinodesu.calculaconta.infra.config;

import dev.kinodesu.calculaconta.domain.command.BillingCommand;
import dev.kinodesu.calculaconta.domain.command.RoomUserCommand;
import dev.kinodesu.calculaconta.domain.command.impl.BillingCommandImpl;
import dev.kinodesu.calculaconta.domain.command.impl.RoomUserCommandImpl;
import dev.kinodesu.calculaconta.domain.usecase.BillingUseCase;
import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import dev.kinodesu.calculaconta.domain.usecase.RoomUseCase;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import dev.kinodesu.calculaconta.domain.usecase.WorkerUseCase;
import dev.kinodesu.calculaconta.domain.usecase.impl.BillingUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.OrderUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.RoomUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.UserUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.WorkerUseCaseImpl;
import dev.kinodesu.calculaconta.infra.dataprovider.OrderDataProvider;
import dev.kinodesu.calculaconta.infra.dataprovider.RoomDataProvider;
import dev.kinodesu.calculaconta.infra.dataprovider.UserDataProvider;
import dev.kinodesu.calculaconta.infra.dataprovider.WorkerDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculaContaConfig {

    @Bean
    public UserUseCase userUseCase(UserDataProvider userDataProvider, RoomUserCommand roomUserCommand) {
        return new UserUseCaseImpl(userDataProvider, roomUserCommand);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderDataProvider orderDataProvider, UserDataProvider userDataProvider) {
        return new OrderUseCaseImpl(orderDataProvider, userDataProvider);
    }

    @Bean
    public RoomUseCase roomUseCase(RoomDataProvider roomDataProvider) {
        return new RoomUseCaseImpl(roomDataProvider);
    }

    @Bean
    public WorkerUseCase workerUseCase(WorkerDataProvider workerDataProvider) {
        return new WorkerUseCaseImpl(workerDataProvider);
    }

    @Bean
    public RoomUserCommand roomUserCommand(RoomUseCase roomUseCase) {
        return new RoomUserCommandImpl(roomUseCase);
    }

    @Bean
    public BillingUseCase billingUseCase(BillingCommand billingCommand) {
        return new BillingUseCaseImpl(billingCommand);
    }

    @Bean
    public BillingCommand billingCommand(UserUseCase userUseCase) {
        return new BillingCommandImpl(userUseCase);
    }
}
