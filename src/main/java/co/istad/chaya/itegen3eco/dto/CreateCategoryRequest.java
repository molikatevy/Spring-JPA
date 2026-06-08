package co.istad.chaya.itegen3eco.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateCategoryRequest(
        @NotBlank(message = "name is required")
        @Size(max = 50)
        String name,
        String description,
        @Size(max = 255)
        String icon,
//        @Size(max = 200)
        @Positive
        @Nullable
        Integer parentCategoryId
) {
}
