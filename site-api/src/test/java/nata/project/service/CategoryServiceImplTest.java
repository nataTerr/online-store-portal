package nata.project.service;

import nata.project.DemoApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = DemoApplication.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

}
