package co.istad.chaya.itegen3eco.features.file;


import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUpLoadService {

    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    private final FileUpLoadRepository fileUpLoadRepository;
    private final FileUploadMapper fileUploadMapper;

    @Override
    public FileUpLoadResponse upload(MultipartFile file) {
        return savefile(file);

    }

    private FileUpLoadResponse savefile(MultipartFile file) {
        // Prepare file information
        // File name
        String name = UUID.randomUUID().toString();

        // mypro.file.png
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        // new-unique-filename.ext

        // Create absolute path to store file
        Path path = Paths.get(storageLocation + name+"."+ext);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been failed to upload");
        }

//        save information file into an
        FileUpLoad fileUpLoad = new FileUpLoad();
        fileUpLoad.setName(name);
        fileUpLoad.setExtansion(ext);
        fileUpLoad.setCaption("ISTAD -Advaced IT Institrute in cambodia");
        fileUpLoad.setSize(file.getSize());
        fileUpLoad.setMediaType(file.getContentType());
        fileUpLoadRepository.save(fileUpLoad);

        return fileUploadMapper.mapFileUploadToFileUploadResponse(fileUpLoad);
    }
    @Override
    public List<FileUpLoadResponse> uploadMultipartFile(List<MultipartFile> files) {
       return files.stream()
               .map(this::savefile)
               .collect(Collectors.toList());
    }

    @Override
    public void deletedByName(String name) {
        FileUpLoad fileUpLoad = fileUpLoadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"File is not found"));
        fileUpLoadRepository.delete(fileUpLoad);

        Path path = Paths.get(storageLocation + name);


        try {
            boolean isExisted = Files.deleteIfExists(path);
            if (!isExisted)
                throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"File is not found");

        }  catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File has been failed to delete");
        }
    }

    @Override
    public Page<FileUpLoadResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortById);

        Page<FileUpLoad> fileUploadResponses = fileUpLoadRepository.findAll(pageRequest);

        return fileUploadResponses.map(fileUploadMapper::mapFileUploadToFileUploadResponse);
    }

    @Override
    public FileUpLoadResponse findByName(String name) {
        return fileUpLoadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadToFileUploadResponse)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"File has not been found"));
    }
}