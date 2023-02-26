package nata.project.service;

import lombok.RequiredArgsConstructor;
import nata.project.dtos.converters.CategoryToDtoConverter;
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

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        return categoryRepository.findAllByParentIdIsNull().stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        return categoryRepository.findById(id)
                .map(categoryConverter::convert)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    @Transactional(readOnly = true)
    // todo: надо переделать, т.к.запрос несет много лишней информации
    public List<CategoryDto> findAllByParentId(Integer parentId) {
        return categoryRepository.findAllById(parentId).stream()
                .map(categoryConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<Integer> getFlatCategoryTree(Integer parentId) {
        return categoryRepository.findAllChildCategoriesByParent(parentId);
    }
}
