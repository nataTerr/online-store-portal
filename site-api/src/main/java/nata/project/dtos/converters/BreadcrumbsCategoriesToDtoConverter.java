package nata.project.dtos.converters;

import nata.project.dtos.response.BreadcrumbsCategoriesDto;
import nata.project.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BreadcrumbsCategoriesToDtoConverter implements Converter<Category, BreadcrumbsCategoriesDto> {

    @Override
    public BreadcrumbsCategoriesDto convert(Category source) {
        BreadcrumbsCategoriesDto breadcrumbsCategoriesDto = new BreadcrumbsCategoriesDto();
        breadcrumbsCategoriesDto.setId(source.getId());
        breadcrumbsCategoriesDto.setCaption(source.getCaption());
        return breadcrumbsCategoriesDto;
    }
}
