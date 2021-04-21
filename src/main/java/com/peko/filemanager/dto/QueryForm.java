package com.peko.filemanager.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author Peko
 */
@Data
public class QueryForm {
    private Date endTime;
    private String newFileName;
    private String oldFileName;
    private Date startTime;
    private String type;
    private String idList;
}
