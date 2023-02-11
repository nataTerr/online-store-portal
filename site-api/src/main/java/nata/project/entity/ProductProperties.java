package nata.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_properties")
@Getter
@Setter
@NoArgsConstructor
public class ProductProperties {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
