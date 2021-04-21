package com.peko.filemanager.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author Peko
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ExcelTarget("myfile")
public class MyFile {
    @Excel(name = "编号")
    private Integer id;
    @Excel(name = "原文件名")
    private String oldFileName;
    @Excel(name = "上传文件名")
    private String newFileName;
    @Excel(name = "文件后缀")
    private String ext;
    @Excel(name = "文件保存路径")
    private String path;
    @Excel(name = "大小")
    private String size;
    @Excel(name = "类型")
    private String type;
    @Excel(name = "是否是图片",replace = {"是_true","否_false"})
    private boolean wonImg;
    @Excel(name = "下载次数")
    private Integer downloadCounts;
    @Excel(name = "上传日期",format = "yyyy-MM-DD HH:mm:ss")
    private Date uploadTime;
}
