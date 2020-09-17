package com.mintlolly.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Create by on jiangbo 2020/9/11 10:13
 * <p>
 * Description:
 */
@Data
@Entity(name="Student")
public class Student {
    @Id
    @Column(name = "SId",nullable = false)
    private String SId;

    @Column(name = "Sname")
    private String sName;

    @Column(name = "Sage")
    private Date sAge;

    @Column(name = "Ssex")
    private String sSex;
}
