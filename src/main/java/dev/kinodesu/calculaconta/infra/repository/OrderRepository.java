package dev.kinodesu.calculaconta.infra.repository;

import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, String> {

    @Query("SELECT o FROM order o WHERE o.room.code = :roomCode")
    List<OrderData> findAllByRoomCode(String roomCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM order o WHERE o.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);
}
