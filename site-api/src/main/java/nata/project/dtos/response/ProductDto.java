package nata.project.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String caption;
    private BigDecimal price;
    private String images;
    private String shortDescription;
}
