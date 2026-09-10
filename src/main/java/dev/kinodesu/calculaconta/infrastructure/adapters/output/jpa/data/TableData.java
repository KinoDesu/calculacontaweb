package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_table")
@Entity(name = "table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TableData extends AuditBaseData {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "table_id", unique = true, length = 36)
    private UUID tableId;

    @Column(name = "table_name", nullable = false)
    private String name;

    @Column(name = "table_code", nullable = false)
    private String code;

    @Column(name = "table_qrcode", unique = true, nullable = false, columnDefinition = "LONGBLOB")
    private byte[] qrCode;

    @Column(name = "table_client_qtd", nullable = false)
    private int clientQuantity;

    @OneToMany(mappedBy = "tableData")
    private List<ClientData> userDataList;

    @OneToMany(mappedBy = "tableData")
    private List<OrderData> orderDataList;
}
