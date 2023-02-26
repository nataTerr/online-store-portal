package nata.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "caption")
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "images")
    private String images;

    @Column(name = "description")
    private String description;

    @Column(name = "short_description")
    private String shortDescription;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "product",
            optional = false
    )
    Price price;

    @OneToMany(mappedBy = "product")
    List<ProductProperties> productProperties;
}
