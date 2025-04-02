package dev.kinodesu.calculaconta.infra.repository;

import dev.kinodesu.calculaconta.infra.repository.data.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

    @Modifying
    @Transactional
    @Query("UPDATE user u SET u.totalAmount = :totalAmount WHERE u.userId = :id")
    void updateTotalAmountById(String id, double totalAmount);
}
