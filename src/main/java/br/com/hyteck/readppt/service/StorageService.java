package br.com.hyteck.readppt.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Set;

public interface StorageService {

    void init();

    String store(MultipartFile file);

    Set<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
