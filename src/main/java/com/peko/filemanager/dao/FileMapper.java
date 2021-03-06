package com.peko.filemanager.dao;

import com.peko.filemanager.dto.QueryForm;
import com.peko.filemanager.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author Peko
 */
@Mapper
public interface FileMapper {
    int insert(MyFile myFile);
    List<MyFile> findAll();
    String findIsMagByid(@Param("id") Integer id);
    MyFile findById(String id);
    List<MyFile> query(QueryForm form);
    List<String> findNewFileName(String newFileName);
    List<String> findOldFileName(String oldFileName);
}
