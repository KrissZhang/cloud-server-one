package com.self.cloudserver.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.self.cloudserver.biz.TestBiz;
import com.self.cloudserver.config.AppContext;
import com.self.cloudserver.constants.ApiUri;
import com.self.cloudserver.constants.Response;
import com.self.cloudserver.constants.ResultEntity;
import com.self.cloudserver.dto.KafkaCommonReq;
import com.self.cloudserver.entity.TestBean;
import com.self.cloudserver.kafka.producer.RecordKafkaProducer;
import com.self.cloudserver.rpc.feign.TestFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "测试管理", tags = "测试管理")
@RestController
public class TestController {

    @Autowired
    private TestFeign testFeign;

    @Autowired
    private RecordKafkaProducer recordKafkaProducer;

    @Autowired
    private TestBiz testBiz;

    @ApiOperation(value = "测试Kafka", notes = "测试Kafka")
    @GetMapping(ApiUri.TEST)
    public void test(@RequestParam String req){
        KafkaCommonReq kafkaCommonReq = new KafkaCommonReq();
        kafkaCommonReq.setKey(req);
        recordKafkaProducer.send(kafkaCommonReq);
    }

    @ApiOperation(value = "测试feign", notes = "测试feign")
    @GetMapping(ApiUri.TEST_FEIGN)
    public Object testFeign(@RequestParam String req){
        return testFeign.rpcTest(req);
    }

    @ApiOperation(value = "测试上传文件", notes = "测试上传文件")
    @GetMapping(ApiUri.TEST_UPLOAD_FILE)
    public Response<JSONObject> testUploadFile(@RequestParam String uploadFileName, @RequestParam String fileFullPath){
        return Response.ok(testBiz.testUploadFile(uploadFileName, fileFullPath));
    }

    @ApiOperation(value = "测试下载", notes = "测试下载")
    @GetMapping(ApiUri.TEST_DOWNLOAD_FILE_CONTENT)
    public Response<JSONObject> testDownloadFileContent(@RequestParam String cloudFileId){
        return Response.ok(testBiz.testDownloadFileContent(cloudFileId));
    }

    @ApiOperation(value = "测试ApplicationContext", notes = "测试ApplicationContext")
    @GetMapping(ApiUri.TEST_APX)
    public ResultEntity<JSONObject> testApx(){
        TestBean testBean = AppContext.getBean(TestBean.class);
        testBean.setId(1L);
        testBean.setName("testBean");
        testBean.setValue("testBean");

        return ResultEntity.ok(JSON.parseObject(JSON.toJSONString(testBean)));
    }

}
