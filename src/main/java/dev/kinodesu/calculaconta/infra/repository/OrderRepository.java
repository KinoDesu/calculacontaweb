package dev.kinodesu.calculaconta.infra.repository;

import dev.kinodesu.calculaconta.infra.repository.data.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderData, String> {
}
