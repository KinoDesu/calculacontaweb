package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Table(name = "tb_order_client")
@Entity(name = "orderClient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClientOrderData {

    @EmbeddedId
    private OrderClientId orderClientId;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "fk_order_id", nullable = false)
    private OrderData orderData;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "fk_client_id", nullable = false)
    private ClientData clientData;

    @Column(name = "client_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;
}
