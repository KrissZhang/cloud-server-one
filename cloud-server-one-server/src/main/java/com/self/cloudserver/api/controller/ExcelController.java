package com.self.cloudserver.api.controller;

import com.google.common.collect.Lists;
import com.self.cloudserver.constants.ApiUri;
import com.self.cloudserver.constants.ResultEntity;
import com.self.cloudserver.dto.excel.Element;
import com.self.cloudserver.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(value = "Excel管理", tags = "Excel管理")
@RestController
public class ExcelController {

    @Value("${excel.path}")
    private String path;

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
    @GetMapping(ApiUri.TEST_EXPORT2FILE)
    public ResultEntity<Object> export2File(){
        List<Element> list = buildData();

        ExcelUtil.export2File(path, "Excel测试实体表", "测试实体", Element.class, list);

        return ResultEntity.ok();
    }

}
