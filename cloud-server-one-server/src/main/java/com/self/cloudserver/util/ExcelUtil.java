package com.self.cloudserver.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.self.cloudserver.constants.ResultEntity;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 导出Excel(07版.xlsx)到指定路径下
     * @param path      路径
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param tClass    Excel要转换的类型
     * @param data      要导出的数据
     */
    public static void exportToFile(String path, String excelName, String sheetName, Class tClass, List data) {
        String fileName = path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(fileName, tClass).sheet(sheetName).doWrite(data);
    }

    /**
     * 导出Excel(07版.xlsx)到web
     * @param response  响应
     * @param excelName Excel名称
     * @param sheetName sheet页名称
     * @param tClass    Excel要转换的类型
     * @param data      要导出的数据
     * @throws Exception Exception
     */
    public static void exportToWeb(HttpServletResponse response, String excelName, String sheetName, Class tClass, List data) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(response.getOutputStream(), tClass).sheet(sheetName).doWrite(data);
    }

    /**
     * 将指定位置指定名称的Excel导出到web
     * @param response  响应
     * @param path      文件路径
     * @param excelName 文件名称
     * @return
     * @throws UnsupportedEncodingException
     */
    public static ResultEntity<Object> exportToWebFile(HttpServletResponse response, String path, String excelName) throws UnsupportedEncodingException {
        File file = new File(path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue()));
        if (!file.exists()) {
            return ResultEntity.addError("500", "文件不存在");
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //这里URLEncoder.encode可以防止中文乱码
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());

        try (
                FileInputStream in = new FileInputStream(file);
                ServletOutputStream out = response.getOutputStream();
        ) {
            IOUtils.copy(in, out);
            return ResultEntity.ok("导出成功");
        } catch (Exception e) {
            logger.error("导出文件异常：", e);
            return ResultEntity.addError("500", e.getMessage());
        }
    }

}
