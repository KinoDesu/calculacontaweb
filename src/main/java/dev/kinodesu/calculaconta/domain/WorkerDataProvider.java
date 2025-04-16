package dev.kinodesu.calculaconta.domain;

import java.time.LocalDateTime;

public interface WorkerDataProvider {
    void clearOldData(LocalDateTime expirationDate);
    void healthCheck();
}
