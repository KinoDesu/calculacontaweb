package dev.kinodesu.calculaconta.infra.repository.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "tb_room")
@Entity(name = "room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class RoomData extends AuditBaseData {

    @Id
    @UuidGenerator
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    @Column(name = "code", unique = true)
    private String code;

    @OneToMany(mappedBy = "room")
    private List<UserData> userList;

    @OneToMany(mappedBy = "room")
    private List<OrderData> orderList;
}
