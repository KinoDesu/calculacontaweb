package dev.kinodesu.calculaconta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private UUID orderId;
    private String name;
    private BigDecimal unitPrice;
    private int quantity;
    private BigDecimal totalPrice;
    private UUID tableId;
    private BigDecimal pricePerPerson;
    private List<UUID> clientIdList;
}