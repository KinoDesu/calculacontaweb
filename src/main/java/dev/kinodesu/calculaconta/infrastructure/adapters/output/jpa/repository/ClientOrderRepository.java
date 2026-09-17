package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository;

import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientOrderData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.OrderClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientOrderRepository extends JpaRepository<ClientOrderData, OrderClientId> {

    List<ClientOrderData> findAllByOrderClientIdOrderId(UUID orderId);

    @Modifying
    @Query("""
                DELETE FROM orderClient c
                WHERE c.orderData.tableData.tableId = :tableId
            """)
    void deleteAllByTableId(UUID tableId);
}
