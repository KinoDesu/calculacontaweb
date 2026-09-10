package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_order")
@Entity(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderData extends AuditBaseData {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "order_id", nullable = false, unique = true, length = 36)
    private UUID orderId;

    @Column(name = "order_item_name", nullable = false)
    private String itemName;

    @Column(name = "order_unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "order_total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "order_item_qtd", nullable = false)
    private int itemQuantity;

    @ManyToOne
    @JoinColumn(name = "fk_table_id", nullable = false)
    private TableData tableData;

    @OneToMany(
            mappedBy = "orderData",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ClientOrderData> clientOrderDataList;
}
