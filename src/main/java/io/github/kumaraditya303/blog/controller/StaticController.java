package io.github.kumaraditya303.blog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.kumaraditya303.blog.entity.DBFile;
import io.github.kumaraditya303.blog.service.DBFileStorageService;

@RestController
public class StaticController {

    private final DBFileStorageService dbFileStorageService;
    @Autowired
    public StaticController(DBFileStorageService dbFileStorageService) {
        this.dbFileStorageService = dbFileStorageService;
    }

    @GetMapping(value = "/static/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getStatic(@PathVariable String fileName) {
        return dbFileStorageService.getRawFile(fileName);
    }

    @PostMapping("/static")
    public DBFile saveStatic(@RequestParam MultipartFile file) throws IOException {
        return dbFileStorageService.store(file);
    }
}
