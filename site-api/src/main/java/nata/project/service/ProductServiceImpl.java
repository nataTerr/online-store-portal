package nata.project.service;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.converters.ProductCardToDtoConverter;
import nata.project.dtos.converters.ProductToDtoConverter;
import nata.project.dtos.response.ProductCardDto;
import nata.project.dtos.response.ProductDto;
import nata.project.entity.Product;
import nata.project.exception.ItemNotFoundException;
import nata.project.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductToDtoConverter productConverter;
    private final CategoryService categoryService;
    private final ProductCardToDtoConverter productCardConverter;

    @Override
    @Transactional(readOnly = true)
    public ProductCardDto findById(long productId) {
        Product productCard = productRepository.fetchProductPriceByProductId(productId)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Product with id %d not found", productId)));
        return productCardConverter.convert(productCard);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getProductsInCategoryGroup(Integer categoryId, Pageable pageable) {
        List<Integer> categoryIds = categoryService.getFlatCategoryTree(categoryId);
        Page<Product> products = productRepository.fetchAllByCategoryIdIn(categoryIds, pageable);
        List<ProductDto> productDtoList = products.getContent().stream()
                .map(productConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtoList, pageable, products.getTotalElements());
    }
}
