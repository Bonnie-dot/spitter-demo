package com.example.demo.Service;

import com.example.demo.Exception.StorageException;
import com.example.demo.Exception.StorageFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties storageProperties){
        this.rootLocation= Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage",e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {

            if (fileName.isEmpty()){
                throw new StorageException("Fail to store empty file:"+fileName);
            }
            if (fileName.contains("..")){
                throw new StorageException("Cannot store file with relative path outside current directory"+fileName);
            }
            try(InputStream inputStream=file.getInputStream()) {
                Files.copy(inputStream,this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            }

        }catch (IOException e){
            throw new StorageException("Failed to store file " + fileName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            Stream<Path> pathStream = Files.walk(this.rootLocation, 1);
            Stream<Path> pathStream1 = pathStream
                    .filter(path -> !path.equals(this.rootLocation));
            return pathStream1
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return this.rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        Path path = load(filename);
        try {
            Resource resource=new UrlResource(path.toUri());
            if (resource.exists()||resource.isReadable()){
                return resource;
            }else{
                throw new StorageFileNotFoundException("cannot read this file:"+filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("cannot read this file:"+filename);
        }
    }
}
