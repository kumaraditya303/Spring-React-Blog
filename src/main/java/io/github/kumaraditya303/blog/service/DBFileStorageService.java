package io.github.kumaraditya303.blog.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.github.kumaraditya303.blog.entity.DBFile;
import io.github.kumaraditya303.blog.repository.DBFileRepository;

@Service
public class DBFileStorageService {
    @Autowired
    DBFileRepository dbFileRepository;

    public DBFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
        return dbFileRepository.save(dbFile);
    }

    public byte[] getFile(String id) {
        return dbFileRepository.findById(id).get().getFile();
    }
}
