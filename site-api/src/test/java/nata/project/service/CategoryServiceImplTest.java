package nata.project.service;

import nata.project.DemoApplication;
import nata.project.repository.CategoryRepository;
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
@Transactional
public class CategoryServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void getCatalog() throws Exception {
        mockMvc.perform(get("/catalog"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$..children.length()", is(17)))
                .andExpect(jsonPath("$[0].id", is(200)))
                .andExpect(jsonPath("$[0].caption", is("Смартфоны и гаджеты")))
                .andExpect(jsonPath("$[0].parentCategory", is(nullValue())))
                .andExpect(jsonPath("$[2].id", is(500)))
                .andExpect(jsonPath("$[2].caption", is("Телевизоры и цифровое ТВ")))
                .andExpect(jsonPath("$[2].parentCategory", is(nullValue())))
                .andExpect(jsonPath("$[2].children.length()", is(2)))
        ;
    }

    @Test
    public void getChildrenCategories() throws Exception {
        mockMvc.perform(get("/catalog/{parentCategoryId}/categories", 500))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$..children.length()", is(4)))
                .andExpect(jsonPath("$[0].id", is(501)))
                .andExpect(jsonPath("$[0].caption", is("Телевизоры")))
                .andExpect(jsonPath("$[0].parentCategory", is(500)))
                .andExpect(jsonPath("$[0].children.length()", is(2)))

                .andExpect(jsonPath("$[0].children.[0].id", is(503)))
                .andExpect(jsonPath("$[0].children.[0].caption", is("QLED-телевизоры")))
                .andExpect(jsonPath("$[0].children.[0].parentCategory", is(501)))
                .andExpect(jsonPath("$[0].children.[0].children.length()", is(0)))
                .andExpect(jsonPath("$[0].children.[1].id", is(502)))
                .andExpect(jsonPath("$[0].children.[1].caption", is("Смарт-телевизоры")))
                .andExpect(jsonPath("$[0].children.[1].parentCategory", is(501)))
                .andExpect(jsonPath("$[0].children.[1].children.length()", is(0)))

                .andExpect(jsonPath("$[1].id", is(504)))
                .andExpect(jsonPath("$[1].caption", is("Цифровое ТВ")))
                .andExpect(jsonPath("$[1].parentCategory", is(500)))
                .andExpect(jsonPath("$[1].children.length()", is(2)))
                .andExpect(jsonPath("$[1].children.[0].id", is(506)))
                .andExpect(jsonPath("$[1].children.[0].caption", is("Приставки")))
                .andExpect(jsonPath("$[1].children.[0].parentCategory", is(504)))
                .andExpect(jsonPath("$[1].children.[0].children.length()", is(0)))
                .andExpect(jsonPath("$[1].children.[1].id", is(505)))
                .andExpect(jsonPath("$[1].children.[1].caption", is("Триколор")))
                .andExpect(jsonPath("$[1].children.[1].parentCategory", is(504)))
                .andExpect(jsonPath("$[1].children.[1].children.length()", is(0)))
        ;
    }

    @Test
    public void checkForNonExistentParentCategory() throws Exception {
        mockMvc.perform(get("/catalog/{parentCategoryId}/categories", 50))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)))
        ;
    }
}
