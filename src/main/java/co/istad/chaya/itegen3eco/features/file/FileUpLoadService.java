package co.istad.chaya.itegen3eco.features.file;

import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUpLoadService {

    FileUpLoadResponse upload(MultipartFile file);
   List< FileUpLoadResponse> uploadMultipartFile(MultipartFile[] file);
   void deletedByName(String name);


}
