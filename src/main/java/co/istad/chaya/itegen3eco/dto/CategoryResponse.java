package co.istad.chaya.itegen3eco.dto;


import co.istad.chaya.itegen3eco.domain.Category;
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
