package com.self.cloudserver.api.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.self.cloudserver.constants.ApiUri;
import com.self.cloudserver.constants.ResultEntity;
import com.self.cloudserver.excel.bean.Element;
import com.self.cloudserver.excel.listener.ElementDataListener;
import com.self.cloudserver.iservice.ElementIService;
import com.self.cloudserver.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Api(value = "Excel管理", tags = "Excel管理")
@RestController
public class ExcelController {

    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Value("${excel.path}")
    private String path;

    @Autowired
    private ElementIService elementIService;

    private List<Element> buildData(){
        List<Element> dataList = Lists.newArrayList();

        Element element = new Element();
        element.setId(1L);
        element.setName("1-1");
        element.setCurDate(new Date());
        dataList.add(element);

        Element element2 = new Element();
        element2.setId(2L);
        element2.setName("1-2");
        element2.setCurDate(new Date());
        dataList.add(element2);

        return dataList;
    }

    @ApiOperation(value = "测试导出Excel到指定路径", notes = "测试导出Excel到指定路径")
    @GetMapping(ApiUri.TEST_EXPORTTOFILE)
    public ResultEntity<Object> exportToFile(){
        List<Element> list = buildData();

        ExcelUtil.exportToFile(path, "Excel测试实体表", "测试实体", Element.class, list);

        return ResultEntity.ok();
    }

    @ApiOperation(value = "测试导出Excel到web", notes = "测试导出Excel到web")
    @GetMapping(ApiUri.TEST_EXPORTTOWEB)
    public void exportToWeb(HttpServletResponse response){
        List<Element> list = buildData();

        try{
            ExcelUtil.exportToWeb(response, "Excel测试实体表", "测试实体", Element.class, list);
        }catch (Exception e){
            logger.error("导出异常：", e);
        }
    }

    @ApiOperation(value = "测试将指定位置的Excel导出到web", notes = "测试将指定位置的Excel导出到web")
    @GetMapping(ApiUri.TEST_EXPORTTOWEBFILE)
    public ResultEntity<Object> exportToWebFile(HttpServletResponse response, @RequestParam String excelName){
        try{
            return ExcelUtil.exportToWebFile(response, path, excelName);
        }catch (Exception e){
            logger.error("导出异常：", e);
            return ResultEntity.addError("500", e.getMessage());
        }
    }

    @ApiOperation(value = "测试读取Excel文件并存库", notes = "测试读取Excel文件并存库")
    @GetMapping(ApiUri.TEST_READANDSAVEEXCELDATA)
    public ResultEntity<Object> readAndSaveExcelData(@RequestParam String excelName){
        String fileName = path.concat(excelName).concat(ExcelTypeEnum.XLSX.getValue());
        EasyExcel.read(fileName, Element.class, new ElementDataListener(elementIService)).sheet().doRead();

        return ResultEntity.ok();
    }

    @ApiOperation(value = "测试上传Excel文件并存库", notes = "测试上传Excel文件并存库")
    @PostMapping(ApiUri.TEST_UPLOADANDSAVEEXCELDATA)
    public ResultEntity<Object> uploadAndSaveExcelData(MultipartFile multipartFile) throws IOException {
        EasyExcel.read(multipartFile.getInputStream(), Element.class, new ElementDataListener(elementIService)).sheet().doRead();

        return ResultEntity.ok();
    }

}
