package com.peko.filemanager.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.peko.filemanager.dto.FileNameDTO;
import com.peko.filemanager.dto.QueryForm;
import com.peko.filemanager.entity.MyFile;
import com.peko.filemanager.service.FileService;
import com.peko.filemanager.service.impl.FileServiceImpl;
import org.apache.ibatis.reflection.ArrayUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
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
        return lists;
    }

    @CrossOrigin
    @PostMapping(value = "/findNewFileName")
    @ResponseBody
    public List<String> findNewFileName(FileNameDTO dto) throws IOException {
        logger.info(String.valueOf(dto));
        List<String> lists =  fileService.findNewFileName(dto.getNewFileName());
        logger.info(String.valueOf(lists));
        return lists;
    }

    @CrossOrigin
    @PostMapping(value = "/findOldFileName")
    @ResponseBody
    public List<String> findOldFileName(FileNameDTO dto) throws IOException {
        logger.info(String.valueOf(dto));
        List<String> lists =  fileService.findOldFileName(dto.getOldFileName());
        logger.info(String.valueOf(lists));
        return lists;
    }

    @CrossOrigin
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(String idList, HttpServletResponse response) throws IOException {
        logger.info(String.valueOf(idList));
        fileService.exportExcel(idList,response);
    }

}
