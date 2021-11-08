package com.self.cloudserver.event.publisher;

import com.self.cloudserver.event.TestEvent1;
import com.self.cloudserver.event.TestEvent2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 测试事件发布器
 */
@Component
public class TestPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布测试事件1
     * @param data 事件参数
     */
    public void publishTestEvent1(String data){
        applicationEventPublisher.publishEvent(new TestEvent1(this, data));
    }

    /**
     * 发布测试事件2
     * @param data 事件参数
     */
    public void publishTestEvent2(String data){
        applicationEventPublisher.publishEvent(new TestEvent2(this, data));
    }

}
