package com.self.cloudserver.kafka.producer;

import com.self.cloudserver.dto.KafkaCommonReq;
import com.self.cloudserver.kafka.constants.TopicConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class RecordKafkaProducer {

    private static Logger logger = LoggerFactory.getLogger(RecordKafkaProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value(TopicConstant.TEST_RECORD)
    private String topic;

    public void send(KafkaCommonReq req){
        try{
            kafkaTemplate.send(topic, req.getKey(), req);
        }catch (Exception e){
            logger.error("请求失败,topic:{},key:{},exception:{}", topic, req.getKey(), req);
        }
    }

}
