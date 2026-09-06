package dev.kinodesu.calculaconta.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequestDTO {
    private UUID clientId;
    private String name;
    private UUID tableId;
    private boolean isBot;
}
