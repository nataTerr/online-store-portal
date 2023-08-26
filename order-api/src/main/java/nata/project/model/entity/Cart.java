package nata.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "items_number")
    private Integer itemsNumber;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToMany(mappedBy = "cart", orphanRemoval = true)
    private List<CartItems> cartItems;
}
