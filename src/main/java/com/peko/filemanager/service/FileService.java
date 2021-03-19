package com.peko.filemanager.service;

import com.peko.filemanager.dto.QueryForm;
import com.peko.filemanager.entity.MyFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Peko
 */
public interface FileService {
    Map<String,String> upload(MultipartFile file) throws IOException;
    List<MyFile> list();
    void download(String id, HttpServletResponse response) throws IOException;
    List<MyFile> query(QueryForm form);
    List<String> findNewFileName(String newFileName);
    List<String> findOldFileName(String oldFileName);
}
