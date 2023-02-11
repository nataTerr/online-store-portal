package nata.project.dtos.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductCardDto {
    private Long id;
    private String caption;
    private BigDecimal price;
    private String images;
    private String description;
    private List<ProductPropertiesDto> productProperties;
}
