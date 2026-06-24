package co.istad.chaya.itegen3eco.features.file;


import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUpLoadService fileUpLoadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FileUpLoadResponse upload(@RequestPart MultipartFile file){
        return fileUpLoadService.upload(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/multiple")
    public List<FileUpLoadResponse> uploadMultiple(@RequestPart MultipartFile[] files){
        return fileUpLoadService.uploadMultipartFile(files);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name){
        fileUpLoadService.deletedByName(name);
    }


}
