package co.istad.chaya.itegen3eco.features.category.dto;


import co.istad.chaya.itegen3eco.features.category.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        String description,
        String icon,
        Boolean isDeleted,
        Category parentCategory,
        Integer parentId
) {
}
