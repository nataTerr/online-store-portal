package nata.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "price")
@Getter
@Setter
@NoArgsConstructor
public class Price implements Serializable {
    @Id
    @Column(name = "product_id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
