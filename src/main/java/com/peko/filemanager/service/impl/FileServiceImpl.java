package com.peko.filemanager.service.impl;

import com.peko.filemanager.dao.FileMapper;
import com.peko.filemanager.entity.MyFile;
import com.peko.filemanager.service.FileService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Peko
 */
@Service
public class FileServiceImpl implements FileService{

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 当前运行的环境(dev or prod)
     */
    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 上传文件的基本目录(dev or prod)
     */
    @Value("${file.basedir}")
    private String basedir;

    @Resource
    private FileMapper fileMapper;

    @Override
    public Map<String,String> upload(MultipartFile file) throws IOException {

        Map<String,String> result = new HashMap<String,String>(16);

        //获取文件原始名称
        String oldFileName = file.getOriginalFilename();
        //获取文件后缀
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名称
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-","")+extension;
        //文件大小（单位为字节）
        Long size = file.getSize();
        //文件类型
        String type = file.getContentType();

        //根据日期生成目录
        String dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String filePath = "/file/" + dataFormat;
        //总路径 = 基本路径 + 文件路径
        String parentDir = basedir + filePath;
        File dir = new File(parentDir);
        if(!dir.exists()){
            dir.mkdirs();
        }

        //处理文件上传
        file.transferTo(new File(dir,newFileName));

        //保存文件信息
        MyFile myFile = new MyFile();
        myFile.setOldFileName(oldFileName);
        myFile.setNewFileName(newFileName);
        myFile.setExt(extension);
        myFile.setSize(String.valueOf(size));
        myFile.setType(type);
        myFile.setPath(filePath);
        myFile.setUploadTime(new Date());
        assert type != null;
        myFile.setWonImg(type.startsWith("image"));

        logger.info(String.valueOf(myFile));

        //将文件信息存入数据库
        int i = fileMapper.insert(myFile);
        if(i == 0){
            logger.error("数据插入失败");
            result.put("status","FAIL");
            return result;
        }

        result.put("fileName",newFileName);
        result.put("status","SUCCESS");

        return result;
    }

    @Override
    public List<MyFile> list() {
        return fileMapper.findAll();
    }

    @Override
    public void download(String id, HttpServletResponse response) throws IOException {
        MyFile myFile = fileMapper.findById(id);
        if(myFile == null){
            logger.info("未找到此文件：id = "+id);
            return;
        }

        String path = basedir + myFile.getPath() +"/"+ myFile.getNewFileName();
        FileInputStream is = new FileInputStream(new File(path));
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(myFile.getOldFileName(),"UTF-8"));
        ServletOutputStream os = response.getOutputStream();

        IOUtils.copy(is,os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);


    }

}
