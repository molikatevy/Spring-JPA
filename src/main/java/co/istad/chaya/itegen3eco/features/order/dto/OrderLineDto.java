package co.istad.chaya.itegen3eco.features.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineDto(
        @NotBlank(message = "code is required")
        String code,
        @Positive
        @NotNull(message = "qty is required")
        Integer qty,
        @NotNull(message = "unitPrice is required")
        @Positive
        BigDecimal unitPrice

        ) {
}
