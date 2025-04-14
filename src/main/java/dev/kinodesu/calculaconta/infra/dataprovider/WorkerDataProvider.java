package dev.kinodesu.calculaconta.infra.dataprovider;

import java.time.LocalDateTime;

public interface WorkerDataProvider {
    void clearOldData(LocalDateTime expirationDate);
    void healthCheck();
}
