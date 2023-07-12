package nata.project.service.data;

import nata.project.entity.Category;
import nata.project.entity.Price;
import nata.project.entity.Product;
import nata.project.entity.ProductProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelperForMockitoData {

    public static List<Integer> listCategoryIds() {
        return Arrays.asList(200, 201, 202);
    }

    public static List<Category> listCategoriesForBreadcrumbs_id503() {
        List<Category> breadcrumbs = new ArrayList<>();

        Category id500 = new Category();
        id500.setId(500);
        id500.setParentId(null);
        id500.setCaption("Телевизоры и цифровое ТВ");

        Category id501 = new Category();
        id501.setId(501);
        id501.setParentId(500);
        id501.setCaption("Телевизоры");

        Category id503 = new Category();
        id503.setId(503);
        id503.setParentId(501);
        id503.setCaption("QLED-телевизоры");

        breadcrumbs.add(id500);
        breadcrumbs.add(id501);
        breadcrumbs.add(id503);
        return breadcrumbs;
    }

    public static List<Category> emptyList() {
        return new ArrayList<>();
    }

    public static List<ProductProperties> createProductProperties(Long id) {
        List<ProductProperties> productProperties = new ArrayList<>();
        ProductProperties p1 = new ProductProperties();
        p1.setId(id);
        ProductProperties p2 = new ProductProperties();
        p2.setId(id);
        productProperties.add(p1);
        productProperties.add(p2);
        return productProperties;
    }

    public static Price createPrice(Long id, Integer sum) {
        Price price = new Price();
        price.setId(id);
        price.setPrice(BigDecimal.valueOf(sum));
        return price;
    }

    public static Product createProduct(Long id, Integer price) {
        Product product = new Product();
        product.setId(id);
        product.setImages(null);
        product.setPrice(createPrice(id, price));
        product.setProductProperties(createProductProperties(id));
        return product;
    }

    public static Page<Product> dataForFindAllProductsByCategoryId200() {
        List<Product> products = new ArrayList<>();
        Product productId1 = createProductWithId1();
        Product productId2 = createProductWithId2();
        products.add(productId1);
        products.add(productId2);
        return new PageImpl<>(products);
    }

    public static Product createProductWithId1() {
        Product productById1 = createProduct(1L, 20000);
        productById1.setCaption("Смартфон Tecno Camon 19 Neo 6/128Gb Eco");
        productById1.setDescription("Смартфон Tecno Camon 19 Neo в корпусе черного цвета оборудован восьмиядерным " +
                "процессором Mediatek Helio и выполняет свои функции на платформе Android 12. " +
                "Дисплей размером 6,78 дюйма и разрешением 1080х2460 пикселей выполнен по технологии IPS. " +
                "Размер оперативной памяти смартфона — 6 Гб, встроенной — 128 Гб.");
        productById1.setShortDescription("Смартфон Tecno Camon 19 Neo в корпусе черного цвета оборудован " +
                "восьмиядерным процессором Mediatek Helio");
        productById1.getProductProperties().get(0).setCaption("Корпус, цвет");
        productById1.getProductProperties().get(0).setValue("черный");
        productById1.getProductProperties().get(1).setCaption("Дисплей, размер");
        productById1.getProductProperties().get(1).setValue("6,78");
        return productById1;
    }

    public static Product createProductWithId2() {
        Product productById2 = createProduct(2L, 50000);
        productById2.setCaption("Смартфон Apple iPhone 14 Pro 256GB Space Black");
        productById2.setDescription("Смартфон Apple iPhone 14 Pro в корпусе цвета Space Black со встроенной " +
                "памятью объемом 256 Гб оборудован OLED-дисплеем диагональю 6,1 дюйма с адаптивной частотой " +
                "обновления до 120 Гц.");
        productById2.setShortDescription("Смартфон Apple iPhone 14 Pro в корпусе цвета Space Black со " +
                "встроенной памятью объемом 256 Гб.");
        productById2.getProductProperties().get(0).setCaption("Корпус, цвет");
        productById2.getProductProperties().get(0).setValue("черный");
        productById2.getProductProperties().get(1).setCaption("Дисплей, размер");
        productById2.getProductProperties().get(1).setValue("6,1");
        return productById2;
    }
}
