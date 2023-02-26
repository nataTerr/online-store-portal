package nata.project.dtos.converters;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.response.ProductCardDto;
import nata.project.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductCardToDtoConverter implements Converter<Product, ProductCardDto> {
    private final ProductPropertiesToDtoConverter productPropertiesConverter;

    @Override
    public ProductCardDto convert(Product source) {
        ProductCardDto productCardDto = new ProductCardDto();
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
