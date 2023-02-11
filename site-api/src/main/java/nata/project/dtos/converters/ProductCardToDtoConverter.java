package nata.project.dtos.converters;

import nata.project.dtos.response.ProductCardDto;
import nata.project.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductCardToDtoConverter implements Converter<Product, ProductCardDto> {

    @Override
    public ProductCardDto convert(Product source) {
        ProductCardDto productCardDto = new ProductCardDto();
        ProductPropertiesToDtoConverter productPropertiesConverter = new ProductPropertiesToDtoConverter();
        productCardDto.setId(source.getId());
        productCardDto.setCaption(source.getCaption());
        productCardDto.setDescription(source.getDescription());
        productCardDto.setPrice(source.getPrice().getPrice());
        productCardDto.setProductProperties(source.getProductProperties().stream()
                .map(productPropertiesConverter::convert)
                .collect(Collectors.toList())
        );
        return productCardDto;
    }
}
