package dev.kinodesu.calculaconta.infra.repository.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

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

    @Column(name = "order_unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "order_total_price", nullable = false)
    private double totalPrice;

    @Column(name = "order_item_qtd", nullable = false)
    private int itemQuantity;

    @Column(name = "order_person_price", nullable = false)
    private double personPrice;

    @ManyToOne
    @JoinColumn(name = "fk_table_id", nullable = false)
    private TableData tableData;

    @ManyToMany
    @JoinTable(
            name = "tb_order_client",
            joinColumns = @JoinColumn(name = "fk_order_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_client_id")
    )
    private List<ClientData> clientDataList;
}
