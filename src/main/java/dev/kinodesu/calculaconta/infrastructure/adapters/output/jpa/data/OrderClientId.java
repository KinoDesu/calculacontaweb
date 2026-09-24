package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderClientId implements Serializable {
    @Column(name = "fk_client_id", length = 36)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID clientId;

    @Column(name = "fk_order_id", length = 36)
    @JdbcTypeCode(Types.VARCHAR)
    private UUID orderId;
}
