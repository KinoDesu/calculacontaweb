package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository;

import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.TableData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TableRepository extends JpaRepository<TableData, UUID> {

    @Query("""
                SELECT t
                FROM table t
                WHERE t.code = :tableCode
            """)
    Optional<TableData> findByCode(String tableCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM table t WHERE t.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);

    @Query("SELECT t.qrCode FROM table t WHERE t.tableId = :tableId")
    byte[] findQrCodeByTableId(UUID tableId);
}
