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
        if (file != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            DBFile dbFile = new DBFile();
            dbFile.setFile(file.getBytes());
            dbFile.setFileName(fileName);
            dbFile.setFileType(file.getContentType());
            return dbFileRepository.save(dbFile);
        }
        return null;
    }

    public byte[] getRawFile(String id) {
        return dbFileRepository.findById(id).get().getFile();
    }

    public DBFile getFile(String id) {
        return dbFileRepository.findById(id).get();
    }
}
