package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository;

import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, UUID> {

    @Query("""
                SELECT o
                FROM order o
                JOIN FETCH o.clientOrderDataList
                WHERE o.tableData.tableId = :tableId
            """)
    List<OrderData> findAllByTableId(UUID tableId);

    @Modifying
    @Transactional
    @Query("DELETE FROM order o WHERE o.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM order o WHERE o.tableData.tableId = :tableId")
    void deleteAllByTableId(UUID tableId);
}
