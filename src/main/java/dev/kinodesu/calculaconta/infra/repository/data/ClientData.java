package dev.kinodesu.calculaconta.infra.repository.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Table(name = "tb_client")
@Entity(name = "client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClientData extends AuditBaseData{

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "client_id", nullable = false, unique = true, length = 36)
    private UUID clientId;

    @Column(name = "client_name", nullable = false)
    private String name;

    @Column(name="is_bot", nullable = false)
    private boolean isBot;

    @ManyToOne
    @JoinColumn(name = "fk_table_id", nullable = false)
    private TableData tableData;

    @ManyToMany(mappedBy = "clientDataList")
    private List<OrderData> orderDataList;
}
