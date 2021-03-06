package io.github.kumaraditya303.blog.controller;

import java.io.IOException;

import javax.validation.constraints.NotNull;

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
public class PostController {

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @GetMapping(value = "/thumbnail/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String fileName) {
        return dbFileStorageService.getFile(fileName);
    }

    @PostMapping(value = "/image")
    public DBFile createImage(@NotNull @RequestParam("image") MultipartFile file) throws IOException {
        return dbFileStorageService.store(file);
    }
}
