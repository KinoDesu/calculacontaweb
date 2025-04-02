package dev.kinodesu.calculaconta.infra.config;

import dev.kinodesu.calculaconta.domain.usecase.OrderUseCase;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import dev.kinodesu.calculaconta.domain.usecase.impl.OrderUseCaseImpl;
import dev.kinodesu.calculaconta.domain.usecase.impl.UserUseCaseImpl;
import dev.kinodesu.calculaconta.infra.dataprovider.OrderDataProvider;
import dev.kinodesu.calculaconta.infra.dataprovider.UserDataProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public UserUseCase userUseCase(UserDataProvider userDataProvider) {
        return new UserUseCaseImpl(userDataProvider);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderDataProvider orderDataProvider, UserDataProvider userDataProvider) {
        return new OrderUseCaseImpl(orderDataProvider, userDataProvider);
    }
}
