package co.istad.chaya.itegen3eco.exception;

import lombok.Builder;

@Builder
public record FieldErrorResponse(
        String field,
        String message
) {
}