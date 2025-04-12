package dev.kinodesu.calculaconta.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String orderId;
    private String name;
    private double unitPrice;
    private int quantity;
    private double totalPrice;
    private Room room;
    private List<User> userList;
    private double pricePerPerson;
}