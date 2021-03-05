package com.peko.filemanager.controller;

import com.peko.filemanager.dto.QueryForm;
import com.peko.filemanager.entity.MyFile;
import com.peko.filemanager.service.FileService;
import com.peko.filemanager.service.impl.FileServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Peko
 */

@RestController
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Resource
    private FileService fileService;

    /**
     * 文件上传接口
     * @param file
     * @return
     */
    @CrossOrigin
    @PostMapping(value = "/upload")
    @ResponseBody
    public Map<String,String> upload(MultipartFile file) throws IOException {

        return fileService.upload(file);
    }

    /**
     * 获取所有文件信息
     * @param file
     * @return
     * @throws IOException
     */
    @CrossOrigin
    @GetMapping(value = "/list")
    @ResponseBody
    public List<MyFile> list() throws IOException {

        return fileService.list();
    }

    @CrossOrigin
    @GetMapping(value = "/download")
    @ResponseBody
    public void download(String id, HttpServletResponse response) throws IOException {
        logger.info(id);
        fileService.download(id,response);
    }

    @CrossOrigin
    @PostMapping(value = "/query")
    @ResponseBody
    public List<MyFile> query(QueryForm form, HttpServletResponse response) throws IOException {
        logger.info(String.valueOf(form));
        List<MyFile> lists =  fileService.query(form);
        logger.info(String.valueOf(lists));
        return lists;
    }

}
