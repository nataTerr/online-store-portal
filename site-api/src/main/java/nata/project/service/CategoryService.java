package nata.project.service;

import nata.project.dtos.response.BreadcrumbsCategoriesDto;
import nata.project.dtos.response.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getCatalog();

    List<BreadcrumbsCategoriesDto> breadcrumbs(Integer id);

    List<CategoryDto> findAllByParentId(Integer parentId);

    List<Integer> getFlatCategoryTree(Integer parentId);
}
