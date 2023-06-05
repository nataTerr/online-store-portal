package nata.project.service.data;

import lombok.RequiredArgsConstructor;
import nata.project.entity.Category;
import nata.project.entity.Price;
import nata.project.entity.Product;
import nata.project.entity.ProductProperties;
import nata.project.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HelperForGeneratingData {

    private final CategoryRepository categoryRepository;

    public static Price createPrice(Long id) {
        Price price = new Price();
        price.setId(id);
        price.setPrice(Randomizer.generateBigDecimalFromRange(BigDecimal.valueOf(10000), BigDecimal.valueOf(30000)));
        return price;
    }

    public static List<ProductProperties> productProperties(Long id) {
        List<ProductProperties> productProperties = new ArrayList<>();
        ProductProperties p1 = new ProductProperties();
        p1.setId(id);
        p1.setCaption(Randomizer.generateString(5));
        p1.setValue(Randomizer.generateString(5));
        ProductProperties p2 = new ProductProperties();
        p2.setId(id);
        p2.setCaption(Randomizer.generateString(5));
        p2.setValue(Randomizer.generateString(5));
        productProperties.add(p1);
        productProperties.add(p2);
        return productProperties;
    }

    private Optional<Category> getCategoryId() {
        return categoryRepository.findById(200);
    }

    public Product createProduct() {
        Product product = new Product();
        product.setCategory(getCategoryId().get());
        product.setCaption(Randomizer.generateStringWithDataFactory(20));
        product.setDescription(Randomizer.generateStringWithDataFactory(50));
        product.setShortDescription(Randomizer.generateStringWithDataFactory(30));
        product.setImages(null);
        return product;
    }
}
