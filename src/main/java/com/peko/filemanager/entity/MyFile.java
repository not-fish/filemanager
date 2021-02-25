package com.peko.filemanager.entity;

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
public class MyFile {
    private Integer id;
    private String oldFileName;
    private String newFileName;
    private String ext;
    private String path;
    private String size;
    private String type;
    private boolean wonImg;
    private Integer downloadCounts;
    private Date uploadTime;
}
