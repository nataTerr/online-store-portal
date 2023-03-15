package nata.project.controller;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.response.ProductCardDto;
import nata.project.dtos.response.ProductDto;
import nata.project.service.ProductServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping("/products/categories/{categoryId}")
    public Page<ProductDto> getProductsInCategoryGroup(@PathVariable Integer categoryId, Pageable pageable) {
        return productService.getProductsInCategoryGroup(categoryId, pageable);
    }

    @GetMapping(value = "/products/{productId}/detail/info")
    public ProductCardDto getProductCard(@PathVariable long productId) {
        return productService.findProductById(productId);
    }
}
