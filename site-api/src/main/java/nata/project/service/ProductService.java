package nata.project.service;

import nata.project.dtos.response.ProductCardDto;
import nata.project.dtos.response.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductCardDto findById(long productId);

    Page<ProductDto> getProductsInCategoryGroup(Integer categoryId, Pageable pageable);
}
