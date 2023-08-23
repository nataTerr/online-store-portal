package nata.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nata.project.model.enums.Delivery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "delivery_address")
@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_delivery")
    private Delivery orderDelivery;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "flat")
    private Integer flat;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "deliveryAddress",
            optional = false
    )
    Orders orders;
}
