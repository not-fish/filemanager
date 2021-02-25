package com.peko.filemanager.service;

import com.peko.filemanager.entity.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Peko
 */
public interface FileService {
    Map<String,String> upload(MultipartFile file) throws IOException;
    List<MyFile> list();
}