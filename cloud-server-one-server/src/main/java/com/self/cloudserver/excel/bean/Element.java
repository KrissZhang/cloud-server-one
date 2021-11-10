package com.self.cloudserver.excel.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * excel测试实体
 */
public class Element {

    @ExcelIgnore
    @TableId(type = IdType.AUTO)
    private Long id;

    @ExcelProperty(value = "测试名称", index = 0)
    private String name;

    @DateTimeFormat(value = "yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "测试日期", index = 1)
    private Date curDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCurDate() {
        return curDate;
    }

    public void setCurDate(Date curDate) {
        this.curDate = curDate;
    }

}
