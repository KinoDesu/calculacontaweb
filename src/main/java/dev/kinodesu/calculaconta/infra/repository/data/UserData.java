package dev.kinodesu.calculaconta.infra.repository.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "tb_user")
@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserData extends AuditBaseData{

    @Id
    @UuidGenerator
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "totalAmount")
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomData room;

    @ManyToMany(mappedBy = "userList")
    private List<OrderData> orders;
}
