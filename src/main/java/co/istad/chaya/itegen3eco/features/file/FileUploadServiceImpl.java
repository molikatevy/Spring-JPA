package co.istad.chaya.itegen3eco.features.file;


import co.istad.chaya.itegen3eco.features.file.dto.FileUpLoadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUpLoadService {

    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    private final FileUpLoadRepository fileUpLoadRepository;

    @Override
    public FileUpLoadResponse upload(MultipartFile file) {

        // Prepare file information
        // File name
        String name = UUID.randomUUID().toString();

        // mypro.file.png
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        name += "." + ext; // new-unique-filename.ext

        // Create absolute path to store file
        Path path = Paths.get(storageLocation + name);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been failed to upload");
        }

        return FileUpLoadResponse.builder()
                .name(name)
                .size(file.getSize())
                .mediaType(file.getContentType())
                .uri(baseUri + name)
                .build();
    }

    @Override
    public List<FileUpLoadResponse> uploadMultipartFile(MultipartFile[] files) {
        List<FileUpLoadResponse> responses = new ArrayList<>();

        if (files == null || files.length == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No files provided");
        }

        for (MultipartFile file : files) {
            responses.add(upload(file));
        }

        return responses;
    }

    @Override
    public void deletedByName(String name) {
        // Delete physical file - search for any file that starts with the name
        Path uploadPath = Paths.get(storageLocation);
        boolean fileFound = false;
        String foundFileName = null;

        try {
            // List all files in the directory
            try (var stream = Files.list(uploadPath)) {
                for (Path filePath : (Iterable<Path>) stream::iterator) {
                    String fileName = filePath.getFileName().toString();
                    // Check if the file starts with the name (followed by a dot)
                    if (fileName.startsWith(name + ".")) {
                        Files.delete(filePath);
                        fileFound = true;
                        foundFileName = fileName;
                        break;
                    }
                }
            }

            if (!fileFound) {
                // If no file starts with the name, try exact match
                Path path = Paths.get(storageLocation + name);
                if (Files.exists(path)) {
                    Files.delete(path);
                    fileFound = true;
                } else {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "File not found for name: " + name);
                }
            }

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to delete file: " + e.getMessage());
        }

        // Delete from database
        FileUpLoad fileUpLoad = fileUpLoadRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "File record not found: " + name));
        fileUpLoadRepository.delete(fileUpLoad);
    }
}