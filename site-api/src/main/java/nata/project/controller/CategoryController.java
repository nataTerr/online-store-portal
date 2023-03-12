package nata.project.controller;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.response.BreadcrumbsCategoriesDto;
import nata.project.dtos.response.CategoryDto;
import nata.project.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/catalog")
    public List<CategoryDto> getCatalog() {
        return categoryService.getCatalog();
    }

    @GetMapping("/catalog/breadcrumbs/{categoryId}")
    public List<BreadcrumbsCategoriesDto> breadcrumbs(@PathVariable Integer categoryId) {
        return categoryService.breadcrumbs(categoryId);
    }

    @GetMapping("/catalog/{parentCategoryId}/categories")
    public List<CategoryDto> getCategoriesByParentId(@PathVariable Integer parentCategoryId) {
        return categoryService.findAllByParentId(parentCategoryId);
    }
}
