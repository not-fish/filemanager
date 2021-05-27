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

    @Test
    public void test02(){
        int arr1[] = new int[]{50,400,400,300,120,300,100,150,100,100,150,120,160,100,100,400,300,300,300,400,160,150,240,100};

        int sum = 0;
        for (int value : arr1) {
            sum += value;
        }

        System.out.println(sum);
    }
}
