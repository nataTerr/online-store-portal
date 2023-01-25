package nata.project.dtos.response;

import lombok.Data;

import java.util.Set;

@Data
public class CategoryDto {
    private int id;
    private String caption;
    private Integer parentCategory;
    private Set<CategoryDto> children;
}
