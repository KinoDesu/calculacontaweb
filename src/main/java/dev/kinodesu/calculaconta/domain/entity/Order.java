package dev.kinodesu.calculaconta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private double unitPrice;
    private int quantity;
    private double totalPrice;
    private UUID tableId;
    private double pricePerPerson;
    private List<Client> clientList;
}