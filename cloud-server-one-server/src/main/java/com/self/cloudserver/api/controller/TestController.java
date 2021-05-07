package com.self.cloudserver.api.controller;

import com.self.cloudserver.constants.ApiUri;
import com.self.cloudserver.dto.KafkaCommonReq;
import com.self.cloudserver.kafka.producer.RecordKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RecordKafkaProducer recordKafkaProducer;

    @GetMapping(ApiUri.TEST)
    public void test(@RequestParam String req){
        KafkaCommonReq kafkaCommonReq = new KafkaCommonReq();
        kafkaCommonReq.setKey(req);
        recordKafkaProducer.send(kafkaCommonReq);
    }

}
