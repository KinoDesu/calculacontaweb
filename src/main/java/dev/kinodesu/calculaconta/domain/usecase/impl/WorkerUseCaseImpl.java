package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.usecase.WorkerUseCase;
import dev.kinodesu.calculaconta.infra.dataprovider.WorkerDataProvider;

import java.time.Duration;
import java.time.LocalDateTime;

public class WorkerUseCaseImpl implements WorkerUseCase {

    private final WorkerDataProvider workerDataProvider;


    public WorkerUseCaseImpl(WorkerDataProvider workerDataProvider) {
        this.workerDataProvider = workerDataProvider;
    }

    @Override
    public void run() {
        try {
            while (true) {
                workerDataProvider.clearOldData(LocalDateTime.now().minusDays(2));
                workerDataProvider.healthCheck();
                Thread.sleep(Duration.ofMinutes(1).toMillis());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
