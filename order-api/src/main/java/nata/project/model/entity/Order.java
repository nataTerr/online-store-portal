package nata.project.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nata.project.model.enums.OrderStatus;
import nata.project.model.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_date_tz")
    private String createDateTZ;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "delivery_date_tz")
    private String deliveryDateTZ;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private DeliveryAddress deliveryAddress;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrderItems> ordersItems;
}
