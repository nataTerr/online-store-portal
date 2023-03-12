package nata.project.service;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.converters.BreadcrumbsCategoriesToDtoConverter;
import nata.project.dtos.converters.CategoryToDtoConverter;
import nata.project.dtos.response.BreadcrumbsCategoriesDto;
import nata.project.dtos.response.CategoryDto;
import nata.project.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToDtoConverter categoryConverter;
    private final BreadcrumbsCategoriesToDtoConverter breadcrumbsConverter;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getCatalog() {
        return categoryRepository.findAllByParentIdIsNull().stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<BreadcrumbsCategoriesDto> breadcrumbs(Integer categoryId) {
        return categoryRepository.breadcrumbs(categoryId).stream()
                .map(breadcrumbsConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    // todo: надо переделать, т.к.запрос несет много лишней информации
    public List<CategoryDto> findAllByParentId(Integer parentId) {
        return categoryRepository.findAllByParentId(parentId).stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getFlatCategoryTree(Integer parentId) {
        return categoryRepository.findAllChildCategoriesByParent(parentId);
    }
}
