package dev.kinodesu.calculaconta.infra.repository;

import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.infra.repository.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

    @Modifying
    @Transactional
    @Query("UPDATE user u SET u.totalAmount = :totalAmount, u.lastModifiedDate = CURRENT_TIMESTAMP WHERE u.userId = :id")
    void updateTotalAmountById(String id, double totalAmount);

    @Query("SELECT u FROM user u WHERE u.room.code = :roomCode")
    List<UserData> findAllByRoomCode(String roomCode);

    @Modifying
    @Transactional
    @Query("DELETE FROM user u WHERE u.createdDate <= :expirationDate")
    void clearOldData(LocalDateTime expirationDate);
}
