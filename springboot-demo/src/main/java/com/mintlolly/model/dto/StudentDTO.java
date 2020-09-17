package com.mintlolly.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Create by on jiangbo 2020/9/11 10:08
 * <p>
 * Description:
 */
@Data
public class StudentDTO implements Serializable {
    private String sId;
    private String sName;
    private Date sAge;
    private String sSex;

}
