package br.com.hyteck.readppt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class FileServiceStorageImpl implements StorageService {

    private final Path fileStorageLocation;

    @Autowired
    private PptxService pptxService;

    public FileServiceStorageImpl() {
        final var classPathResource = new ClassPathResource("/log");

        this.fileStorageLocation = Paths.get(classPathResource.getPath())
                .toAbsolutePath().normalize();

        init();
    }


    @Override
    public void init() {

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }

    }

    @Override
    public String store(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            pptxService.lerPPT(file.getInputStream());

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Set<Path> loadAll() {

        Set<Path> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.fileStorageLocation)) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(path);
                }
            }

            return fileList;


        } catch (IOException e) {
            throw new RuntimeException("File cant load");
        }


//        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//
//
//        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + filename, ex);
        }
    }

    @Override
    public void deleteAll() {

    }
}
