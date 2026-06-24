package co.istad.chaya.itegen3eco.features.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCategoryRequest(
        @NotBlank(message = "name is required")
        @Size(max = 50)
        String name,
        String description,
        @Size(max = 255)
        String icon
) {
}
