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
    @Column(name = "product_id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "val")
    private String value;
}
