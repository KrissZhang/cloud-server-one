package com.self.cloudserver.event.listener;

import com.self.cloudserver.event.TestEvent1;
import com.self.cloudserver.event.TestEvent2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 测试事件监听器
 */
@Component
public class TestListener {

    @EventListener
    public void testListener1(TestEvent1 testEvent1){
        System.out.println("测试事件1监听：" + testEvent1.getData());
    }

    @EventListener
    public void testListener2(TestEvent2 testEvent2){
        System.out.println("测试事件2监听：" + testEvent2.getData());
    }

}
