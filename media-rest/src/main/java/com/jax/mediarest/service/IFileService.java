package com.jax.mediarest.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService {

    void init();

    void save(MultipartFile file);

    Resource load(String fileName);

    void deleteAll();

    public Stream<Path> loadAll();
}
