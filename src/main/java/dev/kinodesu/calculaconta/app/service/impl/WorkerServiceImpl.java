package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.service.WorkerService;
import dev.kinodesu.calculaconta.domain.usecase.WorkerUseCase;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

    private final WorkerUseCase workerUseCase;

    @Override
    @PostConstruct
    public void run() {
        new Thread(workerUseCase::run).start();
    }
}
