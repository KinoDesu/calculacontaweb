package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository;

import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientData, UUID> {

    @Query("SELECT c FROM client c WHERE c.tableData.tableId = :tableId")
    List<ClientData> findAllByTableId(UUID tableId);

    @Modifying
    @Transactional
    @Query("DELETE FROM client c WHERE c.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);
}
