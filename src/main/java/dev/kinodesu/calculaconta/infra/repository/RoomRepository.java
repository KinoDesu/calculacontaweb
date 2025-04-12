package dev.kinodesu.calculaconta.infra.repository;

import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.infra.repository.data.RoomData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomData, String> {

    @Query("SELECT r FROM room r WHERE r.code = :roomCode AND BINARY(r.code) = BINARY(:roomCode)")
    Optional<RoomData> findByCode(String roomCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM room r WHERE r.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);
}
