package co.istad.chaya.itegen3eco.features.file;

import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileUploadMapper {
    @Value("${file.base-uri}")
    private String baseUri;

   public FileUpLoadResponse mapFileUploadToFileUploadResponse(FileUpLoad fileUpLoad){
       return FileUpLoadResponse.builder()
               .name(fileUpLoad.getName())
               .extansion(fileUpLoad.getExtansion())
               .size(fileUpLoad.getSize())
               .mediaType(fileUpLoad.getMediaType())
               .uri(baseUri + fileUpLoad.getName() + "." + fileUpLoad.getExtansion())
               .build();
   }
}
