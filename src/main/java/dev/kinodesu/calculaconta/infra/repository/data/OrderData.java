package dev.kinodesu.calculaconta.infra.repository.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "tb_order")
@Entity(name = "order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderData {

    @Id
    @UuidGenerator
    @Column(name = "id")
    private String orderId;

    @Column(name = "name")
    private String name;

    @Column(name = "unitPrice")
    private double unitPrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToMany
    @JoinTable(
            name = "order_user",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserData> userList;

    @Column(name = "pricePerPerson")
    private double pricePerPerson;
}
