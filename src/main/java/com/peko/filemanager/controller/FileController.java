package com.peko.filemanager.controller;

import com.peko.filemanager.entity.MyFile;
import com.peko.filemanager.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Peko
 */

@Controller
@RequestMapping("/file")
public class FileController {

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
        List<MyFile> lists = fileService.list();
        for(MyFile list:lists){
            System.out.println(list);
        }

        return lists;
    }
}
