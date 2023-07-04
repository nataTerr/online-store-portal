package nata.project.service;

import nata.project.DemoApplication;
import nata.project.entity.Product;
import nata.project.exception.ItemNotFoundException;
import nata.project.repository.ProductRepository;
import nata.project.service.data.HelperForGeneratingData;
import nata.project.service.data.Randomizer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
public class ProductServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private HelperForGeneratingData generateProduct;

    @Test
    @Transactional
    public void getProductCard() throws Exception {
        //given
        Product product = generateAndSaveProduct();
        //then
        mockMvc.perform(get("/products/{productId}/detail/info", product.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is((product.getId().intValue()))))
                .andExpect(jsonPath("$.price", is(product.getPrice().getPrice().doubleValue())))
                .andExpect(jsonPath("$.images", is(is(nullValue()))))
                .andExpect(jsonPath("$.caption", is(product.getCaption())))
                .andExpect(jsonPath("$.productProperties.length()", is(2)))
                .andExpect(jsonPath("$.productProperties.[0].caption",
                        is(product.getProductProperties().get(0).getCaption())))
                .andExpect(jsonPath("$.productProperties.[0].value",
                        is(product.getProductProperties().get(0).getValue())))
                .andExpect(jsonPath("$.productProperties.[1].caption",
                        is(product.getProductProperties().get(1).getCaption())))
                .andExpect(jsonPath("$.productProperties.[1].value",
                        is(product.getProductProperties().get(1).getValue())))
        ;
    }

    @Test
    public void checkForNonExistentProductId() throws Exception {
        //given
        Integer productId = Randomizer.generateIntegerFromRange(1000, 2000);
        //then
        mockMvc.perform(get("/products/{productId}/detail/info", productId))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(ItemNotFoundException.class))
                .andExpect(mvcResult -> mvcResult.getResolvedException().getMessage()
                        .equals(String.format("Product with id %d not found", productId)))
        ;
    }

    private Product generateAndSaveProduct() {
        Product product = generateProduct.createProduct();
        productRepository.save(product);
        product.setPrice(HelperForGeneratingData.createPrice(product.getId()));
        product.setProductProperties(HelperForGeneratingData.productProperties(product.getId()));
        return product;
    }
}
