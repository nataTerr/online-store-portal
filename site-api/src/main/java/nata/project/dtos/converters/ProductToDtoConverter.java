package nata.project.dtos.converters;

import nata.project.dtos.response.ProductDto;
import nata.project.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductToDtoConverter implements Converter<Product, ProductDto> {

    @Override
    public ProductDto convert(Product source) {
        ProductDto productDto = new ProductDto();
        productDto.setId(source.getId());
        productDto.setCaption(source.getCaption());
        productDto.setPrice(source.getPrice().getPrice());
        productDto.setShortDescription(source.getShortDescription());
        return productDto;
    }
}
