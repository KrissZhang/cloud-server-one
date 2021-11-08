package com.self.cloudserver.event;

import org.springframework.context.ApplicationEvent;

/**
 * 测试事件1
 */
public class TestEvent1 extends ApplicationEvent {

    private String data;

    public TestEvent1(Object source) {
        super(source);
    }

    public TestEvent1(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
