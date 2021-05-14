package com.peko.filemanager;

import com.peko.filemanager.dao.FileMapper;
import com.peko.filemanager.entity.MyFile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Peko
 */
@SpringBootTest
public class Test01 {

    @Resource
    private FileMapper fileMapper;

    @Test
    public void test01(){
        List<MyFile> lists = fileMapper.findAll();
        System.out.println(lists);
    }
}
