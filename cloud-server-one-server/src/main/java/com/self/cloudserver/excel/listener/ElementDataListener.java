package com.self.cloudserver.excel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.self.cloudserver.excel.bean.Element;
import com.self.cloudserver.iservice.ElementIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ElementDataListener extends AnalysisEventListener<Element> {

    private static final Logger logger = LoggerFactory.getLogger(ElementDataListener.class);

    private ElementIService elementIService;

    public ElementDataListener(ElementIService elementIService) {
        this.elementIService = elementIService;
    }

    private static final Integer BATCH_COUNT = 5;

    private List<Element> dataList = Lists.newArrayList();

    private void batchSaveData(){
        if(!CollectionUtils.isEmpty(dataList)){
            logger.info("{}条数据开始存库", dataList.size());
            elementIService.saveBatch(dataList);
            logger.info("存库成功");
        }
    }

    @Override
    public void invoke(Element element, AnalysisContext analysisContext) {
        logger.info("解析到一条数据：{}", JSON.toJSONString(element));

        dataList.add(element);
        if(dataList.size() >= BATCH_COUNT){
            batchSaveData();
            dataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        batchSaveData();
        logger.info("===全部数据解析存库成功===");
    }

}
