package co.istad.chaya.itegen3eco.features.file;

import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUpLoadService {

    FileUpLoadResponse upload(MultipartFile file);
    List< FileUpLoadResponse> uploadMultipartFile(List<MultipartFile> file);
    void deletedByName(String name);
    Page<FileUpLoadResponse> findAll(int pageNumber, int pageSize);
    FileUpLoadResponse findByName (String name);

}
