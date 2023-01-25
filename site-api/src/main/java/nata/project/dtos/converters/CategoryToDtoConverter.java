package nata.project.dtos.converters;

import nata.project.dtos.response.CategoryDto;
import nata.project.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryToDtoConverter implements Converter<Category, CategoryDto> {

    @Override
    public CategoryDto convert(Category source) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(source.getId());
        categoryDto.setCaption(source.getCaption());
        categoryDto.setParentCategory(source.getParentId());
        categoryDto.setChildren(source.getChildren().stream()
                .map(this::convert)
                .collect(Collectors.toSet())
        );
        return categoryDto;
    }
}
