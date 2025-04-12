package dev.kinodesu.calculaconta.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderRequestDTO {
    private String name;
    private double unitPrice;
    private int quantity;
    private String roomCode;
    private List<UUID> userList;
}
