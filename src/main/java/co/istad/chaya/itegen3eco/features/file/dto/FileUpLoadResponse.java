package co.istad.chaya.itegen3eco.features.file.dto;

import lombok.Builder;

@Builder
public record FileUpLoadResponse(
        String name,
        String caption,
        Long size,
        String mediaType,
//        http://localhost:8080/file/istad
        String uri
) {
}
