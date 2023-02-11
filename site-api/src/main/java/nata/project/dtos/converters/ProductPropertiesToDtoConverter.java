package nata.project.dtos.converters;

import nata.project.dtos.response.ProductPropertiesDto;
import nata.project.entity.ProductProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductPropertiesToDtoConverter implements Converter<ProductProperties, ProductPropertiesDto> {

    @Override
    public ProductPropertiesDto convert(ProductProperties source) {
        ProductPropertiesDto productPropertiesDto = new ProductPropertiesDto();
        productPropertiesDto.setCaption(source.getCaption());
        productPropertiesDto.setValue(source.getValue());
        return productPropertiesDto;
    }
}
