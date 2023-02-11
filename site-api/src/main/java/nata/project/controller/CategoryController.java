package nata.project.controller;

import lombok.RequiredArgsConstructor;
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
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/catalog/breadcrumbs/{categoryId}")
    public CategoryDto findById(@PathVariable Integer categoryId) {
        return categoryService.findById(categoryId);
    }

    @GetMapping("/catalog/{parentCategoryId}/categories")
    public List<CategoryDto> findAllByParentId(@PathVariable Integer parentCategoryId) {
        return categoryService.findAllByParentId(parentCategoryId);
    }
}
