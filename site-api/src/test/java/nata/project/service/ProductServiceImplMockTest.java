package nata.project.service;

import nata.project.DemoApplication;
import nata.project.repository.CategoryRepository;
import nata.project.repository.ProductRepository;
import nata.project.service.data.HelperForMockitoData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@ExtendWith({MockitoExtension.class})
@Transactional
public class ProductServiceImplMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    public void checkConvertProductDto() throws Exception {
        //given
        List<Integer> categoryIds = HelperForMockitoData.listCategoryIds();
        Pageable pageable = PageRequest.of(0, 20);
        Mockito.when(categoryRepository.findAllChildCategoriesByParent(200))
                .thenReturn(categoryIds);
        Mockito.when(productRepository.fetchAllByCategoryIdIn(categoryIds, pageable))
                .thenReturn(HelperForMockitoData.dataForFindAllProductsByCategoryId200());
        //then
        mockMvc.perform(get("/products/categories/{categoryId}", 200, pageable))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content.length()", is(2)))
                .andExpect(jsonPath("$.content[0].images", is(is(nullValue()))))
                .andExpect(jsonPath("$.content[0].caption", is("Смартфон Tecno Camon 19 Neo 6/128Gb Eco")))
                .andExpect(jsonPath("$.content[0].price", is(20000)))
                .andExpect(jsonPath("$.content[1].price", is(50000)))
        ;
    }
}
