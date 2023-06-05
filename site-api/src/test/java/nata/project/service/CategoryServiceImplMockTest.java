package nata.project.service;

import nata.project.DemoApplication;
import nata.project.repository.CategoryRepository;
import nata.project.service.data.HelperForMockitoData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
@ExtendWith({MockitoExtension.class})
@Transactional
public class CategoryServiceImplMockTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void getBreadcrumbs() throws Exception {
        //given
        Mockito.when(categoryRepository.breadcrumbs(503))
                .thenReturn(HelperForMockitoData.listCategoriesForBreadcrumbs_id503());
        //then
        mockMvc.perform(get("/catalog/breadcrumbs/{categoryId}", 503))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)))
                .andExpect(jsonPath("$[0].id", is(500)))
                .andExpect(jsonPath("$[0].caption", is("Телевизоры и цифровое ТВ")))
                .andExpect(jsonPath("$[1].id", is(501)))
                .andExpect(jsonPath("$[1].caption", is("Телевизоры")))
        ;
    }

    @Test
    public void checkBreadcrumbsForNonExistentCategory() throws Exception {
        //given
        Mockito.when(categoryRepository.breadcrumbs(50))
                .thenReturn(HelperForMockitoData.nullList());
        //then
        mockMvc.perform(get("/catalog/breadcrumbs/{categoryId}", 50))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)))
        ;
    }
}
