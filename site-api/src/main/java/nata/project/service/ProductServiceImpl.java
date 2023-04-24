package nata.project.service;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.converters.ProductCardToDtoConverter;
import nata.project.dtos.converters.ProductToDtoConverter;
import nata.project.dtos.response.ProductCardDto;
import nata.project.dtos.response.ProductDto;
import nata.project.entity.Product;
import nata.project.exception.ItemNotFoundException;
import nata.project.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductToDtoConverter productConverter;
    private final CategoryService categoryService;
    private final ProductCardToDtoConverter productCardConverter;

    @Override
    @Transactional(readOnly = true)
    public ProductCardDto findProductById(long productId) {
        logger.info("Product information with id {} " +
                "(method call fetchProductPriceByProductId in ProductRepository)", productId);
        Product productCard = productRepository.fetchProductPriceByProductId(productId)
                .orElseThrow(() ->
                        new ItemNotFoundException(String.format("Product with id %d not found", productId)));
        return productCardConverter.convert(productCard);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> getProductsInCategoryGroup(Integer categoryId, Pageable pageable) {
        List<Integer> categoryIds = categoryService.getFlatCategoryTree(categoryId);
        logger.info("Find all products in parent {} and child categories " +
                "(method call fetchAllByCategoryIdIn in ProductRepository)", categoryId);
        Page<Product> products = productRepository.fetchAllByCategoryIdIn(categoryIds, pageable);
        List<ProductDto> productDtoList = products.getContent().stream()
                .map(productConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtoList, pageable, products.getTotalElements());
    }
}
